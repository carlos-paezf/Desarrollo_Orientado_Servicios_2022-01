import { Request, Response } from 'express'
import PensumDAO from '../daos/pensum.dao';
import { SQL_PENSUM } from '../repositories/pensum.repository';


class PensumController extends PensumDAO {
    /**
     * This is a function that will be called when the user request the `/pensums` endpoint.
     * @param {Request} req - Request - The request object that contains the request parameters.
     * @param {Response} res - Response - The response object that is passed back to the client.
     */
    public getPensums = (req: Request, res: Response): void => {
        PensumDAO.getPensums(SQL_PENSUM.ALL, [], res)
    }

    /**
     * This is a function that will be called when the user request the `/pensums/create-pensum` endpoint.
     * @param {Request} req - Request - The request object that contains the request parameters.
     * @param {Response} res - Response - The response object that is passed back to the client.
     */
    public postPensum = (req: Request, res: Response): void => {
        const { programId, pensumName } = req.body
        const params = [programId, pensumName]
        PensumDAO.postPensum(SQL_PENSUM.CONFIRM_PROGRAM, SQL_PENSUM.CONFIRM_PENSUM, SQL_PENSUM.CREATE, params, res)
    }
}


const pensumController = new PensumController()
export default pensumController