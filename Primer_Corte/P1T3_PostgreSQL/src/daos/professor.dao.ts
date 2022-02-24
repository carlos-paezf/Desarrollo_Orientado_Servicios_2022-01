import { Response } from 'express'
import { red } from 'colors'

import connectionDB from '../config/connection/connection_DB'


class ProfessorDAO {
    /**
     * A function that will be used by the class `ProfessorDAO` to get the data from the database.
     */
    protected static getProfessors = async (sqlQuery: string, parameters: any, res: Response): Promise<any> => {
        try {
            const answer = await connectionDB.pool.result(sqlQuery, parameters)
            res.status(200).json({ ok: true, resultsQuery: answer.rows })
        } catch (error) {
            console.log(red('Error in ProfessorDAO: '), error)
            res.status(400).json({ ok: false })
        }
    }
}


export default ProfessorDAO