import { Request, Response } from 'express'

import { SubjectDAO_Get } from "../../daos/subjects";
import { SQL_SUBJECT_SELECT } from "../../repositories/subjects";


class SubjectController_Get extends SubjectDAO_Get {
    /**
     * This is a function that will be called when the user request the `/subjects` endpoint.
     * It gets all subjects from the database.
     * @param {Request} req - Request - the request object that contains the request parameters.
     * @param {Response} res - Response - the response object that is passed back to the client.
     */
    public getSubjects = (req: Request, res: Response): void => {
        SubjectController_Get.getSubjects(SQL_SUBJECT_SELECT.ALL, [], res)
    }


    /**
     * This a function that will be called when the user request the `/subjects/:subjectId` endpoint, with the verb GET
     * @param {Request} req - Request - The request object that contains the request parameters
     * @param {Response} res - Response  The response object that is passed back to the client.
     */
    public getOneSubjectById = (req: Request, res: Response): void => {
        const { subjectId } = req.params
        SubjectController_Get.getOneSubjectById(SQL_SUBJECT_SELECT.CONFIRM_ID, SQL_SUBJECT_SELECT.SELECT_ONE, [subjectId], res)
    }
}


export const subjectControllerGet = new SubjectController_Get()