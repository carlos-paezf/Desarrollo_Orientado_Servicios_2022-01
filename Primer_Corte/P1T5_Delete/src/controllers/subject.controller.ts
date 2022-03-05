'use strict'

import { Request, Response } from 'express'
import SubjectDAO from '../daos/subject.dao'
import { SQL_SUBJECT } from '../repositories/subject.sql';


class SubjectController extends SubjectDAO {
    /**
     * This is a function that will be called when the user request the `/subjects` endpoint.
     * It gets all subjects from the database.
     * @param {Request} req - Request - the request object that contains the request parameters.
     * @param {Response} res - Response - the response object that is passed back to the client.
     */
    public getSubjects = (req: Request, res: Response): void => {
        SubjectDAO.getSubjects(SQL_SUBJECT.ALL, [], res)
    }

    /**
     * This is a function that will be called when the user request the `/create-subject` endpoint. 
     * @param {Request} req -  Request - the request object that contains the request parameters.
     * @param {Response} res -  Response - the response object that is passed back to the client.
     */
    public postSubject = (req: Request, res: Response): void => {
        const { subjectName, subjectReference } = req.body
        const params = [subjectName, subjectReference]
        SubjectDAO.postSubject(SQL_SUBJECT.CONFIRM, SQL_SUBJECT.CREATE, params, res)
    }
}


const subjectController = new SubjectController()
export default subjectController