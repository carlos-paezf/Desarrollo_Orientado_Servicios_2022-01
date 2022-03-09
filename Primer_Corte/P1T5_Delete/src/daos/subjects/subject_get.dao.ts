import { Response } from 'express'
import { red } from 'colors'
import connectionDB from '../../config/connection/connection_DB'


export class SubjectDAO_Get {
    /**
     * This is a function that will be user to get the semesters information.
     * @param {string} sqlQuery - The SQL query to execute 
     * @param {any} parameters 
     * @param {Response} res - Response - The response object that is passed in by the controller. 
     * @returns If everything is fine, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": true,
     *      "resultsQuery": [
     *          {
     *              "subjectId": 1,
     *              "subjectName": "Desarrollo Orientado a Servicios",
     *              "subjectReference": "ISDOS"
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
    protected static getSubjects = async (sqlQuery: string, parameters: any, res: Response): Promise<any> => {
        try {
            const { rows } = await connectionDB.pool.result(sqlQuery, parameters)
            return res.status(200).json({ ok: true, resultsQuery: rows })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el administrador' })
        }
    }


    /**
     * It gets one subject by id.
     * @param {string} sqlConfirm - The SQL query that will be executed to confirm if the subject exists.
     * @param {string} sqlQuery - The query to be executed.
     * @param {any} params - any
     * @param {Response} res - Response
     * @returns If everything is fine, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": true,
     *      "data": {
     *          "subjectId": "1",
     *          "subjectName": "DOS",
     *          "subjectReference": "ISDOS" 
     *      }
     * }
     * ```
     * If the subject does not exists, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": true,
     *      "msg": "No existe una materia con el id 0"
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
    protected static getOneSubjectById = async (sqlConfirm: string, sqlQuery: string, params: any, res: Response): Promise<any> => {
        try {
            const { amount } = await connectionDB.pool.one(sqlConfirm, params)
            if (amount === '0') return res.status(400).json({ ok: false, msg: `No existe ninguna materia con el id ${params[0]}` })
            const { subjectId, subjectName, subjectReference } = await connectionDB.pool.one(sqlQuery, params)
            return res.status(200).json({ ok: true, data: { subjectId, subjectName, subjectReference } })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }
}