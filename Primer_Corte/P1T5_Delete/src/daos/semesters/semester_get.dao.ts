import { Response } from 'express'
import { red } from 'colors'
import connectionDB from '../../config/connection/connection_DB';


export class SemesterDAO_Get {
    /**
     * This is a function that will be user to get the semesters information.
     * @param {string} sqlQuery - The SQL query to execute.
     * @param {any} parameters
     * @param {Response} res - Response - The response object that is passed in by the controller.
     * @returns If everything is fine, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": true,
     *      "resultsQuery": [
     *          {
     *              "semesterId": 1,
     *              "semesterName": "Semestre 1"
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
    protected static getSemesters = async (sqlQuery: string, parameters: any, res: Response): Promise<any> => {
        try {
            const { rows } = await connectionDB.pool.result(sqlQuery, parameters)
            return res.status(200).json({ ok: true, resultsQuery: rows })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }


    protected static getOneSemesterById = async (sqlConfirmId: string, sqlQuery: string, params: any, res: Response): Promise<any> => {
        try {
            const { amount } = await connectionDB.pool.one(sqlConfirmId, params)
            if (amount === '0') return res.status(400).json({ ok: false, msg: `No hay ningún semestre con el id ${params[0]}` })
            const { semesterId, semesterName } = await connectionDB.pool.one(sqlQuery, params)
            return res.status(200).json({ ok: true, data: { semesterId, semesterName } })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }
}