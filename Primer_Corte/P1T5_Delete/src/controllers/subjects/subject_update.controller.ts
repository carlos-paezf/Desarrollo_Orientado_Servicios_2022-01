import { Request, Response } from 'express'
import { SubjectDAO_Update } from '../../daos/subjects'
import { SQL_SUBJECT_SELECT, SQL_SUBJECT_UPDATE } from '../../repositories/subjects'


class SubjectController_Update extends SubjectDAO_Update {
    /**
     * This is a function that will be called when the user request the `/update-subject/:subjectId` endpoint. 
     * @param {Request} req - Request - The request object that contains the request parameters.
     * @param {Response} res - Response - The response object that is passed back to the client.
     * @returns If the body is a empty string, the response is a JSON object with the following structure:
     * ```json
     * {
     *      "ok": false,
     *      "msg": "No se pueden enviar campos vacíos"
     * }
     * ```
     */
    public updateOneSubjectById = (req: Request, res: Response): void | Promise<any> => {
        const { subjectId } = req.params
        const { subjectName, subjectReference } = req.body

        if (subjectName.trim() === '' || subjectReference.trim() === '') {
            return Promise.resolve(res.status(400).json({ ok: false, msg: 'No se pueden enviar campos vacíos en el formulario' }))
        }

        const params = [subjectId, subjectName, subjectReference]
        SubjectController_Update.updateOneSubjectById(
            SQL_SUBJECT_SELECT.CONFIRM_ID, SQL_SUBJECT_SELECT.CONFIRM_UNIQUE,
            SQL_SUBJECT_UPDATE.UPDATE, params,
            res
        )
    }
}


export const subjectControllerUpdate = new SubjectController_Update()