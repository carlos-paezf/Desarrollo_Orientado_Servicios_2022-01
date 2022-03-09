'use strict'

import { Response } from "express";
import { red } from 'colors'

import connectionDB from "../../config/connection/connection_DB";


export class ProgramDAO_Update {
    /**
     * It updates a program.
     * @param {string} sqlConfirmId - The SQL query to confirm that the program exists.
     * @param {string} sqlConfirmUnique - The SQL query that will be used to confirm that the program name is unique.
     * @param {string} sqlUpdate - The SQL query that will be executed.
     * @param {any} parameters - any[]
     * @param {Response} res - Response
     * @returns If everything is fine, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": true,
     *      "msg": "Se ha actualizado el programa con el id 0",
     *      "affectedRows": 1,
     *      "idProgram": 1
     * }
     * ```
     * If the entered Id does not exists, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": false,
     *      "msg": "No existe un programa con el id 0"
     * }
     * ```
     * If the program name already exists, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": false,
     *      "msg": "No se pueden duplicar los nombres de un programa"
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
    protected static updateProgram = async (sqlConfirmId: string, sqlConfirmUnique: string, sqlUpdate: string, parameters: any, res: Response): Promise<any> => {
        try {
            const { amount: amountId } = await connectionDB.pool.one(sqlConfirmId, parameters[0])
            if (amountId === '0') return res.status(400).json({ ok: false, msg: `No existe un programa con el id ${parameters[0]}` })
            
            const { amount: amountProgram } = await connectionDB.pool.one(sqlConfirmUnique, parameters[1])
            if (amountProgram !== '0') return res.status(400).json({ ok: false, msg: 'No se puede duplicar el nombre de un programa', body: parameters[1] })
            
            const { rowCount } = await connectionDB.pool.result(sqlUpdate, parameters)
            return res.status(201).json({ 
                ok: true, 
                msg: `Se ha actualizado el programa con el id ${parameters[0]}`, 
                idProgram: parameters[0],
                affectedRows: rowCount 
            })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el administrador' })
        }
    }
}