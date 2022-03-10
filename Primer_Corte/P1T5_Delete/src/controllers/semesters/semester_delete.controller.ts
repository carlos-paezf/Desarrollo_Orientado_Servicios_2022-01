import { Request, Response } from "express";
import { SemesterDAO_Delete } from "../../daos/semesters";
import { SQL_SEMESTER_DELETE, SQL_SEMESTER_SELECT } from "../../repositories/semesters";


class SemesterController_Delete extends SemesterDAO_Delete {
    public deleteOneSemesterById = (req: Request, res: Response): void => {
        const { semesterId } = req.params
        SemesterController_Delete.deleteOneSemesterById(SQL_SEMESTER_SELECT.CONFIRM_ID, SQL_SEMESTER_DELETE.DELETE, [semesterId], res)
    }
}


export const semesterControllerDelete = new SemesterController_Delete()