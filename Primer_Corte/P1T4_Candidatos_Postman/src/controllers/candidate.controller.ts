import { Request, Response } from 'express'

import CandidateDAO from '../daos/candidate.dao'
import { SQL_CANDIDATE } from '../repositories/candidate.sql'



class CandidateController extends CandidateDAO {
    /** 
     * This is a function that will be called when the user requests the `/candidates` endpoint. 
     * It gets all candidates from the database.
     * @param {Request} req - Request - the request object that contains the request parameters.
     * @param {Response} res - Response - The response object that is passed back to the client.
     */
    public getCandidates = (req: Request, res: Response): void => {
        CandidateDAO.getCandidates(SQL_CANDIDATE.ALL, [], res)
    }
}


const candidateController = new CandidateController()
export default candidateController