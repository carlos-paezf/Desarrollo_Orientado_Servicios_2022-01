import { Response } from 'express'
import { red } from 'colors'
import connectionDB from '../../config/connection/connection_DB';


export class PensumDAO_Get {
    /**
     * This is a function that will be user to get the pensums information.
     * @param {string} sqlQuery - The SQL query to execute 
     * @param {any} params 
     * @param {Response} res - Response - The response object that is passed by the controller.
     * @returns If everything is fine, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": true,
     *      "resultsQuery": [
     *          {
     *              "pensumId": 1,
     *              "programId": 1,
     *              "pensumName": "Pensum Test"
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
    protected static getPensums = async (sqlQuery: string, params: any, res: Response): Promise<any> => {
        try {
            const { rows } = await connectionDB.pool.result(sqlQuery, params)
            return res.status(200).json({ ok: true, resultsQuery: rows })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }


    protected static getOnePensumById = async (sqlConfirmId: string, sqlQuery: string, params: any, res: Response): Promise<any> => {
        try {
            const { amount } = await connectionDB.pool.one(sqlConfirmId, params)
            if (amount === '0') return res.status(400).json({ ok: false, msg: `No hay ningún pensum con el id ${params[0]}` })
            const data = await connectionDB.pool.one(sqlQuery, params)
            return res.status(200).json({ ok: true, data })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }
}