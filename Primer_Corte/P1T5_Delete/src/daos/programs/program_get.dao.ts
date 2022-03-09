import { Response } from 'express'
import { red } from 'colors'
import connectionDB from "../../config/connection/connection_DB"


export class ProgramDAO_Get {
    /**
     * This is a function that will be used to get the programs information.
     * @param {string} sqlQuery - The SQL query to execute. 
     * @param {any} parameters
     * @param {Response} res - Response - The response object that is passed in by the controller.
     * @returns If everything is fine, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": true,
     *      "resultsQuery": [
     *          {
     *              "programId": 1,
     *              "programName": "Ingeniería de Sistemas"
     *          }
     *      ]
     * }
     * ```
     * Otherwise, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "oh": false,
     *      "msg": "Comuníquese con el Administrador"
     * }
     * ```
     */
    protected static getPrograms = async (sqlQuery: string, parameters: any, res: Response): Promise<any> => {
        try {
            const { rows } = await connectionDB.pool.result(sqlQuery, parameters)
            return res.status(200).json({ ok: true, resultsQuery: rows })
        } catch (error) {
            console.log(red(`Error: `), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }


    /**
     * It gets one program by id.
     * @param {string} sqlConfirmId - The SQL query that will be executed to confirm if the program exists.
     * @param {string} sqlQuery - The query to be executed.
     * @param {any} params - any
     * @param {Response} res - Response
     * @returns If everything is fine, the response is a JSON object with the following structure:
     * ```
     * {
     *     "ok": true,
     *     "data": {
     *         "programId": "1",
     *         "programName": "Programa 1"
     *     }
     * }
     * ```
     * If the program does not exists, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": true,
     *      "msg": "No existe un programa con el id 0"
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
    protected static getOneProgramById = async (sqlConfirmId: string, sqlQuery: string, params: any, res: Response): Promise<any> => {
        try {
            const { amount } = await connectionDB.pool.one(sqlConfirmId, params)
            if (amount === '0') return res.status(400).json({ ok: false, msg: `No existe un programa con el id ${params[0]}` })
            const { programId, programName } = await connectionDB.pool.one(sqlQuery, params)
            return res.status(200).json({ ok: true, data: { programId, programName } })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el administrador' })
        }
    }
}