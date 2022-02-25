import { Request, Response } from "express";
import ProfessorDAO from "../daos/professor.dao";
import { SQL_PROFESSOR } from "../repositories/professor.sql";


class ProfessorController extends ProfessorDAO {
    /* This is a function that will be called when the user requests the `/professors` endpoint. */
    public getProfessors = (req: Request, res: Response): void => {
        ProfessorDAO.getProfessors(SQL_PROFESSOR.ALL, [], res)
    }

    /* This is a function that will be called when the user requests the `/professors/type-and-faculty` endpoint. */
    public getTypeProfessorsAndFaculty = (req: Request, res: Response): void => {
        ProfessorDAO.getProfessors(SQL_PROFESSOR.TYPE_AND_FACULTY, [], res)
    }
}


const professorController = new ProfessorController()
export default professorController