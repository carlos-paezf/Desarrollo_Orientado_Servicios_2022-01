import { Request, Response } from "express";
import { SemesterDAO_Post } from "../../daos/semesters";
import { SQL_SEMESTER_SELECT, SQL_SEMESTER_CREATE } from '../../repositories/semesters';


class SemesterController_Post extends SemesterDAO_Post {
    /**
     * This is a function that will be called when the user request the `/create-semester` endpoint.
     * @param {Request} req - Request - The request object that contains the request parameters
     * @param {Response} res - Response - The response object that is passed back to the client
     */
    public postSemester = (req: Request, res: Response): void => {
        const { semesterName } = req.body
        SemesterController_Post.postSemester(SQL_SEMESTER_SELECT.CONFIRM_UNIQUE, SQL_SEMESTER_CREATE.CREATE, [semesterName], res)
    }
}


export const semesterControllerPost = new SemesterController_Post()