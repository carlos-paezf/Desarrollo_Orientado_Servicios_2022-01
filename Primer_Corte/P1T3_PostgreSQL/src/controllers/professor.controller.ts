import { Request, Response } from "express";
import ProfessorDAO from "../daos/professor.dao";
import { SQL_PROFESSOR } from "../repositories/professor.sql";


class ProfessorController extends ProfessorDAO {
    /* This is a function that will be called when the user requests the `/professors` endpoint. */
    public getProfessors = (req: Request, res: Response): void => {
        ProfessorDAO.getProfessors(SQL_PROFESSOR.ALL, [], res)
    }
}


const professorController = new ProfessorController()
export default professorController