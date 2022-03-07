import { Response } from 'express'
import { red } from 'colors'

import connectionDB from '../config/connection/connection_DB';


class PensumDAO {
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

    /**
     * It creates a new pensum.
     * This is a function that will be used to create a pensum register.
     * @param {string} sqlConfirmProgram - The SQL query to confirm that the program exists.
     * @param {string} sqlConfirmPensum - The SQL query that will be used to confirm if the pensum exists.
     * @param {string} sqlCreate - The SQL query to create the pensum.
     * @param {any} params - an array of parameters to pass to the query.
     * @param {Response} res - Response
     * @returns If everything is fine, the response is a JSON object with the following structure:
     * ```json
     * {
     *     "ok": true,
     *     "msg": "Pensum Creado",
     *     "newId": 1
     * }
     * ```
     * If the program id don't exists, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": false,
     *      "msg": "No existe ningún programa con el id 1"
     * }
     * ```
     * Otherwise, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": false,
     *      "msg": "Pensum ya existente"
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
    protected static postPensum = async (sqlConfirmProgram: string, sqlConfirmPensum: string, sqlCreate: string, params: any, res: Response): Promise<any> => {
        try {
            const { programAmount } = await connectionDB.pool.one(sqlConfirmProgram, params)
            if (parseInt(programAmount) === 0) {
                return res.status(400).json({ ok: false, msg: `No existe ningún programa con el id ${params[0]}` })
            }
            const { pensumId, amount } = await connectionDB.pool.task(async query => {
                const { amount } = await query.one(sqlConfirmPensum, params)
                if (amount === '0') return query.one(sqlCreate, params)
                return { pensumId: 0, amount }
            })
            if (pensumId !== 0) {
                return res.status(201).json({ ok: true, msg: 'Pensum creado', newId: pensumId })
            }
            return res.status(400).json({ ok: false, msg: 'Pensum ya existente', amount })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }
}


export default PensumDAO