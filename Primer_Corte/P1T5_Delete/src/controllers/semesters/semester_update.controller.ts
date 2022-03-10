import { Request, Response } from "express";
import { SemesterDAO_Update } from "../../daos/semesters";
import { SQL_SEMESTER_SELECT, SQL_SEMESTER_UPDATE } from "../../repositories/semesters";


class SemesterController_Update extends SemesterDAO_Update {
    public updateOneSemesterById = (req: Request, res: Response): Promise<any> | void => {
        const { semesterId } = req.params
        const { semesterName } = req.body
        
        if(semesterName.trim() === '') {
            return Promise.resolve(res.status(400).json({ ok: false, msg: 'No se pueden enviar campos vacíos' }))
        }

        SemesterController_Update.updateOneSemesterById(
            SQL_SEMESTER_SELECT.CONFIRM_ID, SQL_SEMESTER_SELECT.CONFIRM_UNIQUE,
            SQL_SEMESTER_UPDATE.UPDATE, [semesterId, semesterName],
            res
        )
    }
}


export const semesterControllerUpdate = new SemesterController_Update()