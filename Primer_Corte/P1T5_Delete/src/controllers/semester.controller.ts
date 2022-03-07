'use strict'

import { Request, Response } from "express";

import SemesterDAO from "../daos/semester.dao";
import { SQL_SEMESTER } from '../repositories/semester.sql';


class SemesterController extends SemesterDAO {
    /**
     * This is a function that will be called when the user request the `/semesters` endpoint.
     * It gets all semesters from the database.
     * @param {Request} req - Request - The request object that contains the request parameters
     * @param {Response} res - Response - The response object that is passed back to the client
     */
    public getSemesters = (req: Request, res: Response): void => {
        SemesterDAO.getSemesters(SQL_SEMESTER.ALL, [], res)
    }

    /**
     * This is a function that will be called when the user request the `/create-semester` endpoint.
     * @param {Request} req - Request - The request object that contains the request parameters
     * @param {Response} res - Response - The response object that is passed back to the client
     */
    public postSemester = (req: Request, res: Response): void => {
        const { semesterName } = req.body
        SemesterDAO.postSemester(SQL_SEMESTER.CONFIRM, SQL_SEMESTER.CREATE, [semesterName], res)
    }
}


const semesterController = new SemesterController()
export default semesterController