import { Request, Response } from 'express'
import { SubjectDAO_Delete } from '../../daos/subjects';
import { SQL_SUBJECT_SELECT } from '../../repositories/subjects/subject_select.sql';
import { SQL_SUBJECT_DELETE } from '../../repositories/subjects/subject_delete.sql';


class SubjectController_Delete extends SubjectDAO_Delete {
    /**
     * This a function that will be called when the user request the `/subjects/:subjectId` endpoint, with the verb DELETE
     * @param {Request} req - Request - The request object that contains the request parameters
     * @param {Response} res - Response  The response object that is passed back to the client.
     */
    public deleteOneSubjectById = (req: Request, res: Response): void => {
        const { subjectId } = req.params
        SubjectController_Delete.deleteOneSubjectById(SQL_SUBJECT_SELECT.CONFIRM_ID, SQL_SUBJECT_DELETE.DELETE, [subjectId], res)
    }
}


export const subjectControllerDelete = new SubjectController_Delete()