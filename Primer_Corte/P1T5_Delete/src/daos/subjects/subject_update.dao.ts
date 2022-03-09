import { Response } from 'express'
import { red } from 'colors'

import connectionDB from '../../config/connection/connection_DB';


export class SubjectDAO_Update {
    /**
     * It updates a subject by id.
     * @param {string} sqlConfirmId - The SQL query that will be used to confirm if the subject exists.
     * @param {string} sqlConfirmUnique - This is the SQL query that will be used to confirm if the subjectReference already exists.
     * @param {string} sqlUpdate - The SQL query that will be executed.
     * @param {any} params - any
     * @param {Response} res - Response
     * @returns If everything is fine, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": true,
     *      "msg": "La materia con el id 0, ha sido actualizada",
     *      "affectedRows": 1,
     *      "body": {
     *          "subjectName": "DOS",
     *          "subjectReference": "ISDOS"
     *      }
     * }
     * ```
     * If the entered Id does not exists, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": false,
     *      "msg": "No existe una materia con el id 0"
     * }
     * ```
     * If the subject references already exists, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": false,
     *      "msg": "No se pueden duplicar las referencias"
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
    protected static updateOneSubjectById = async (sqlConfirmId: string, sqlConfirmUnique: string, sqlUpdate: string, params: any, res: Response): Promise<any> => {
        try {
            const [subjectId, subjectName, subjectReference] = params

            const { amount: amountId } = await connectionDB.pool.one(sqlConfirmId, subjectId)
            if (amountId === '0') return res.status(400).json({ ok: false, msg: `No existe una materia con el id ${params[0]}` })
            
            const { amount: amountSubject } = await connectionDB.pool.one(sqlConfirmUnique, subjectReference)
            if (amountSubject !== '0') return res.status(400).json({ ok: false, msg: 'No se pueden duplicar las referencias' })
    
            const { rowCount } = await connectionDB.pool.result(sqlUpdate, params)
            return res.status(201).json({
                ok: true,
                msg: `La materia con el id ${subjectId}, ha sido actualizada`,
                affectedRows: rowCount,
                body: {
                    subjectName,
                    subjectReference,
                }
            })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }
}