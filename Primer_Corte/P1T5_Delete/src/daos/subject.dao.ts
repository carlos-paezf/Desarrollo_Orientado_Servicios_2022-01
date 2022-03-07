'use strict'

import { Response } from 'express'
import { red } from 'colors'
import connectionDB from '../config/connection/connection_DB'


class SubjectDAO {
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
     * It creates a new Subject
     * This is a function that will be used to creat a semester register.
     * @param {string} sqlConfirm - The SQL query that will be used to check if the semester already exists
     * @param {string} sqlCreate - The SQL query to create the semester
     * @param {any} parameters 
     * @param {Response} res - Response 
     * @returns The subjectId, if it was created, or the amount of semesters that already exists.
     * If everything is fine, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": true,
     *      "msg": "Materia creada",
     *      "newId": 1
     * }
     * ```
     * Otherwise, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": false,
     *      "msg": "Materia ya existente",
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
    protected static postSubject = async (sqlConfirm: string, sqlCreate: string, parameters: any, res: Response): Promise<any> => {
        try {
            const { subjectId, amount } = await connectionDB.pool.task(async query => {
                const { amount } = await query.one(sqlConfirm, parameters)
                if (amount === '0') {
                    return query.one(sqlCreate, parameters)
                } 
                else return { subjectId: 0, amount }
            })
            if (subjectId !== 0) {
                return res.status(201).json({ ok: true, msg: 'Materia creada', subjectId })
            }
            else return res.status(400).json({ ok: false, msg: 'Referencia de Materia ya existente', amount })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el administrador' })
        }
    }
}


export default SubjectDAO