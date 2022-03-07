import { Request, Response } from 'express'
import PensumSubjectDAO from '../daos/pensum-subject.dao';
import { SQL_PENSUM_SUBJECT } from '../repositories/pensum-subject.repository';


class PensumSubjectController extends PensumSubjectDAO {
    /**
     * This is a function that will be called when the user request the `/pensum-subject` endpoint.
     * @param {Request} req - Request - The request object that contains the request parameters.
     * @param {Response} res - Response - The response object that is passed back to the client.
     */
    public getPensumSubject = (req: Request, res: Response) => {
        PensumSubjectDAO.getPensumSubject(SQL_PENSUM_SUBJECT.ALL, [], res)
    }

    /**
     * This is a function that will be called when the user request the `/pensum-subject/create-pensum-subject` endpoint.
     * @param {Request} req - Request - The request object that contains the request parameters.
     * @param {Response} res - Response - The response object that is passed back to the client.
     */
    public postPensumSubject = (req: Request, res: Response) => {
        const { pensumId, subjectId, semesterId } = req.body
        const params = [pensumId, subjectId, semesterId]
        PensumSubjectDAO.postPensumSubject(
            SQL_PENSUM_SUBJECT.CONFIRM_PENSUM,
            SQL_PENSUM_SUBJECT.CONFIRM_SUBJECT,
            SQL_PENSUM_SUBJECT.CONFIRM_SEMESTER,
            SQL_PENSUM_SUBJECT.CONFIRM_PENSUM_SUBJECT,
            SQL_PENSUM_SUBJECT.CREATE,
            params,
            res
        )
    }
}


const pensumSubjectController = new PensumSubjectController()
export default pensumSubjectController