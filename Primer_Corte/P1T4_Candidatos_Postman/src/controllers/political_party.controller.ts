import { Request, Response } from 'express'

import PoliticalPartyDAO from '../daos/political_party.dao';
import { SQL_POLITICAL_PARTY } from '../repositories/political_party.sql'


class PoliticalPartyController extends PoliticalPartyDAO {
    /** 
     * This is a function that will be called when the user requests the `/political-parties` endpoint. 
     * It gets all political parties from the database.
     * @param {Request} req - Request - the request object that contains the request parameters.
     * @param {Response} res - Response - The response object that is passed back to the client.
     */
    public getPoliticalParties = (req: Request, res: Response): void => {
        PoliticalPartyDAO.getPoliticalParties(SQL_POLITICAL_PARTY.ALL, [], res)
    }

    /**
     * This is a function that will be called when the user request the `/political-parties/create` endpoint.
     * It creates a political party.
     * @param {Request} req - Request
     * @param {Response} res - Response - This is the response object that is created when a request is
     * made.
     */
    public postPoliticalParty = (req: Request, res: Response): void => {
        const { politicalPartyName } = req.body
        PoliticalPartyDAO.postPoliticalParty(SQL_POLITICAL_PARTY.CONFIRM, SQL_POLITICAL_PARTY.CREATE, [politicalPartyName], res)
    }
}


const politicalPartyController = new PoliticalPartyController()
export default politicalPartyController