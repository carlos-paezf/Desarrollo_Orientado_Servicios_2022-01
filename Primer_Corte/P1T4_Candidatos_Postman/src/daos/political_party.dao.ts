'use strict'

import { Response } from "express";
import { red } from 'colors'

import connectionDB from "../config/connection/connection_DB";


class PoliticalPartyDAO {
    /**
     * This is a function that will be used to get the political party information. 
     * @param {string} sqlQuery - The SQL query to execute.
     * @param {any} parameters - any
     * @param {Response} res - Response - The response object that is passed in by the controller.
     * @returns If everything id fine, the response is a JSON object with the following structure:
     * ```json
     * {
     *     ok: true,
     *     resultsQuery: [{
     *         "partyId": 1,
     *         "partyName": "Partido 0"
     *     ]}
     * }
     * ```
     * Otherwise, the response is a JSON object with the following structure:
     * ```json
     * {
     *     ok: false
     * }
     * ```
     */
    protected static getPoliticalParties = async (sqlQuery: string, parameters: any, res: Response): Promise<any> => {
        try {
            const answer = await connectionDB.pool.result(sqlQuery, parameters)
            return res.status(200).json({ ok: true, resultsQuery: answer.rows })
        } catch (error) {
            console.log(red(`Error: `), error)
            return res.status(400).json({ ok: false })
        }
    }


    /**
     * It creates a political party.
     * @param {string} sqlConfirm - The SQL query to confirm if the party already exists.
     * @param {string} sqlCreate - The SQL query to create the political party.
     * @param {any} parameters - any
     * @param {Response} res - Response
     * @returns If everything is fine, the response is a JSON object with the following structure:
     * ```json
     * {
     *     ok: true,
     *     msg: 'Partido creado',
     *     newId: 10
     * }
     * ```
     * Otherwise, the response is a JSON object with the following structure:
     * ```json
     * {
     *     ok: false,
     *     msg: 'Partido ya existente',
     *     amount: "1"
     * }
     * ```
     */
    protected static postPoliticalParty = async (sqlConfirm: string, sqlCreate: string, parameters: any, res: Response): Promise<any> => {
        await connectionDB.pool.task(async query=> {
            const { amount } = await query.one(sqlConfirm, parameters)
            if (parseInt(amount) === 0) {
                return await query.one(sqlCreate, parameters)
            }
            else {
                return { partyId: 0, amount }
            }
        })
            .then(({ partyId, amount }) => {
                if (partyId !== 0) {
                    return res.status(201).json({ ok: true, msg: 'Partido creado', newId: partyId })
                }
                else return res.status(400).json({ ok: false, msg: 'Partido ya existente', amount })
            })
            .catch((error: any) => {
                console.log(red('Error'), error)
                return res.status(400).json({ ok: false })
            })
    }
}


export default PoliticalPartyDAO