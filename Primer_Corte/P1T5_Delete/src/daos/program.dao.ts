'use strict'

import { Response } from "express";
import { red } from 'colors'

import connectionDB from "../config/connection/connection_DB";


class ProgramDAO {
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
     * It creates a program.
     * This is a function that will be used to create a program register.
     * @param {string} sqlConfirm - The SQL query to confirm if the program already exists. 
     * @param {string} sqlCreate - The SQL query to create the program.
     * @param {any} parameters 
     * @param {Response} res - Response - The response object that is passed in by the controller.
     * @returns If everything is fine, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": true,
     *      "msg": "Programa creado",
     *      "newId": 1
     * }
     * ```
     * Otherwise, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": false,
     *      "msg": "Programa ya existente",
     *      "amount": "1"
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
    protected static postProgram = async (sqlConfirm: string, sqlCreate: string, parameters: any, res: Response): Promise<any> => {
        await connectionDB.pool.task(async query=> {
            const { amount } = await query.one(sqlConfirm, parameters)
            if (parseInt(amount) === 0) {
                return await query.one(sqlCreate, parameters)
            }
            else {
                return { programId: 0, amount }
            }
        })
            .then(({ programId, amount }) => {
                if (programId !== 0) {
                    return res.status(201).json({ ok: true, msg: 'Programa creado', newId: programId })
                }
                else return res.status(400).json({ ok: false, msg: 'Programa ya existente', amount })
            })
            .catch((error: any) => {
                console.log(red('Error'), error)
                return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
            })
    }
}


export default ProgramDAO