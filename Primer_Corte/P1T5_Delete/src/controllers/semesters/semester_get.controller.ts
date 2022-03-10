import { Request, Response } from "express";
import { SemesterDAO_Get } from '../../daos/semesters';
import { SQL_SEMESTER_SELECT } from '../../repositories/semesters';


class SemesterController_Get extends SemesterDAO_Get {
    /**
     * This is a function that will be called when the user request the `/semesters` endpoint.
     * It gets all semesters from the database.
     * @param {Request} req - Request - The request object that contains the request parameters
     * @param {Response} res - Response - The response object that is passed back to the client
     */
    public getSemesters = (req: Request, res: Response): void => {
        SemesterController_Get.getSemesters(SQL_SEMESTER_SELECT.ALL, [], res)
    }


    public getOneSemesterById = (req: Request, res: Response): void => {
        const { semesterId } = req.params
        SemesterController_Get.getOneSemesterById(SQL_SEMESTER_SELECT.CONFIRM_ID, SQL_SEMESTER_SELECT.SELECT_ONE, [semesterId], res)
    }
}


export const semesterControllerGet = new SemesterController_Get()