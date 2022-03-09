import { Request, Response } from 'express'

import { SubjectDAO_Post } from "../../daos/subjects";
import { SQL_SUBJECT_CREATE, SQL_SUBJECT_SELECT } from '../../repositories/subjects';


class SubjectController_Post extends SubjectDAO_Post {
    /**
     * This is a function that will be called when the user request the `/create-subject` endpoint. 
     * @param {Request} req -  Request - the request object that contains the request parameters.
     * @param {Response} res -  Response - the response object that is passed back to the client.
     */
    public postSubject = (req: Request, res: Response): void => {
        const { subjectName, subjectReference } = req.body
        const params = [subjectName, subjectReference]
        SubjectController_Post.postSubject(SQL_SUBJECT_SELECT.CONFIRM_UNIQUE, SQL_SUBJECT_CREATE.CREATE, params, res)
    }
}


export const subjectControllerPost = new SubjectController_Post()