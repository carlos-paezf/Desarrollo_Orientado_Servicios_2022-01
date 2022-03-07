import { Response } from 'express'
import { red } from 'colors'
import connectionDB from '../config/connection/connection_DB';


class PensumSubjectDAO {
    /**
     * It gets the data from the database.
     * @param {string} sqlQuery - The SQL query to execute.
     * @param {any} params - any
     * @param {Response} res - Response
     * @returns If everything is fine, the response is a JSON object with the following structure:
     * ```json
     * {
     *      ok: true,
     *      resultQuery: [
     *          {
     *              "pensumId": 1,
     *              "pensumName": "Pensum 2022-01",
     *              "semesterId": 1,
     *              "semesterName": "Semestre 1",
     *              "subjectId": 1,
     *              "subjectName": "Desarrollo Orientado a Servicios"
     *          }
     *      ]
     * }
     * ```
     * Otherwise, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": false,
     *      "msg": "Comuníquese con el Administrador"
     * }
     * ```
     */
    protected static getPensumSubject = async (sqlQuery: string, params: any, res: Response) => {
        try {
            const { rows } = await connectionDB.pool.result(sqlQuery, params)
            return res.status(200).json({ ok: true, resultQuery: rows })
        } catch (error) {
            console.log(red('Error'), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el administrador' })
        }
    }

    /**
     * It creates a relationship between a pensum and a subject.
     * @param {string} sqlConfirmPensum - The SQL query to confirm that the pensum exists.
     * @param {string} sqlConfirmSubject - A SQL query that returns the amount of subjects that match the id parameter.
     * @param {string} sqlConfirmSemester - The SQL query to confirm that the semester exists.
     * @param {string} sqlConfirmPensumSubject - This is the SQL query that will be used to confirm if the pensum-subject relationship already exists.
     * @param {string} sqlCreate - The SQL query to create the pensum-subject relationship.
     * @param {any} params - any[]
     * @param {Response} res - Response
     * @returns If everything is fine, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": true,
     *      "msg": "Relación pensum-materia ha sido creada",
     *      "pensumId": 1,
     *      "subjectId": 1,
     *      "semesterId": 1
     * }
     * ```
     * In case some id given in the body does not exist, then the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": false,
     *      "msg": "No hay ____ con el id __"
     * }
     * ```
     * In case the problem is with the backend, the response will be a JSON object with the following structure:
     * ```json
     * {
     *      "ok": false,
     *      "msg": "Comuníquese con el Administrador"
     * }
     * ```
     */
    protected static postPensumSubject = async (sqlConfirmPensum: string, sqlConfirmSubject: string, sqlConfirmSemester: string, sqlConfirmPensumSubject: string, sqlCreate: string, params: any, res: Response) => {
        try {
            const { pensumAmount } = await connectionDB.pool.one(sqlConfirmPensum, params[0])
            const { subjectAmount } = await connectionDB.pool.one(sqlConfirmSubject, params[1])
            const { semesterAmount } = await connectionDB.pool.one(sqlConfirmSemester, params[2])
            if (pensumAmount === '0') return res.status(400).json({ ok: false, msg: `No hay pensum con el id ${params[0]}` })
            if (subjectAmount === '0') return res.status(400).json({ ok: false, msg: `No hay materias con el id ${params[1]}` })
            if (semesterAmount === '0') return res.status(400).json({ ok: false, msg: `No hay semestres con el id ${params[2]}` })

            const { pensumId, subjectId, semesterId } = await connectionDB.pool.task(async query => {
                const { pAmount, sAmount } = await query.one(sqlConfirmPensumSubject, params)
                if (pAmount !== '0' && sAmount !== '0') {
                    return { pensumId: 0, subjectId: 0, semesterId: 0 }
                }
                else return query.one(sqlCreate, params)
            })
            if (pensumId === 0 && subjectId === 0 && semesterId === 0) {
                return res.status(400).json({ ok: false, msg: 'Ya existe una relación del pensum con la materia' })
            }
            return res.status(201).json({ ok: true, msg: 'Relación pensum-materia ha sido creada', pensumId, subjectId, semesterId })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }
}


export default PensumSubjectDAO