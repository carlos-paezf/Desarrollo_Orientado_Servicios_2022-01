'use strict'

import { Response } from 'express'
import { red } from 'colors'

import connectionDB from '../config/connection/connection_DB';


class SemesterDao {
    /**
     * This is a function that will be user to get the semesters information.
     * @param {string} sqlQuery - The SQL query to execute.
     * @param {any} parameters
     * @param {Response} res - Response - The response object that is passed in by the controller.
     * @returns If everything is fine, the response is a JSON object with the following structure:
     * ```json
     * {
     *      ok: true,
     *      resultsQuery: [
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
     *      ok: false,
     *      msg: "Comuníquese con el Administrador"
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


    /**
     * It creates a new semester.
     * This is a functions that will be used to create a semester register.
     * @param {string} sqlConfirm - The SQL query that will be used to check if the semester already
     * exists.
     * @param {string} sqlCreate - The SQL query to create the semester.
     * @param {any} parameters
     * @param {Response} res - Response
     * @returns The semesterId, if it was created, or the amount of semesters that already exist.
     * If everything is fine, the response is a JSON object with the following structure:
     * ```json
     * {
     *      ok: true,
     *      msg: "Semestre creado",
     *      newId: 1
     * }
     * ```
     * Otherwise, the response is a JSON object with the following structure:
     * ```json
     * {
     *      ok: false,
     *      msg: "Semestre ya existente",
     *      amount: "1"
     * }
     * ```
     * In case the problem is with the backend, the response will be a JSON object with the following structure:
     * ```json
     * {
     *      ok: false,
     *      msg: "Comuníquese con el Administrador"
     * }
     * ```
     */
    protected static postSemester = async (sqlConfirm: string, sqlCreate: string, parameters: any, res: Response): Promise<any> => {
        try {
            const { semesterId, amount } = await connectionDB.pool.task(async query => {
                const { amount } = await query.one(sqlConfirm, parameters)
                if (parseInt(amount) === 0) {
                    return await query.one(sqlCreate, parameters)
                } else {
                    return { semesterId: 0, amount }
                }
            })
            if(semesterId !== 0) {
                return res.status(201).json({ ok: true, msg: 'Semestre creado', newId: semesterId })
            } 
            else return res.status(400).json({ ok: false, msg: 'Semestre ya existente', amount })
        } catch (error: any) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }
}


export default SemesterDao