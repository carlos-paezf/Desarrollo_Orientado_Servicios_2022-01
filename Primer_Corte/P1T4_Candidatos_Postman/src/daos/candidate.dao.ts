'use strict'

import { Response } from "express";
import { red } from 'colors'

import connectionDB from "../config/connection/connection_DB";


class CandidateDAO {
    /**
     * This is a function that will be used to get the candidates information. 
     * @param {string} sqlQuery - The SQL query to execute.
     * @param {any} parameters - any
     * @param {Response} res - Response - The response object that is passed in by the controller.
     * @returns The response is a JSON object with the following structure:
     * ```
     * {
     *     ok: true,
     *     resultsQuery: [{
     *         "candidateId": 1,
     *         "candidateName": "Ferrer",
     *         "candidateDateBirth": "2000-01-31T05:00:00.000Z",
     *         "candidateEvaluation": "1.30",
     *         "partyName": "Partido 0"
     *     ]}
     * }
     * ```
     */
    protected static getCandidates = async (sqlQuery: string, parameters: any, res: Response): Promise<any> => {
        try {
            const answer = await connectionDB.pool.result(sqlQuery, parameters)
            return res.status(200).json({ ok: true, resultsQuery: answer.rows })
        } catch (error) {
            console.log(red(`Error: `), error)
            return res.status(400).json({ ok: false })
        }
    }
}


export default CandidateDAO