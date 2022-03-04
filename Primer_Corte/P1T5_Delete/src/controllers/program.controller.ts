import { Request, Response } from 'express'
import ProgramDAO from '../daos/program.dao'
import { SQL_PROGRAMS } from '../repositories/program.sql';


class ProgramController extends ProgramDAO {
    /**
     * This is a function that will be called when the user request the `/programs` endpoint.
     * It gets all programs from the database.
     * @param {Request} req - Request - the request object that contains the request parameters. 
     * @param {Response} res - Response - The response object that is passed back to the client. 
     */
    public getPrograms = (req: Request, res: Response): void => {
        ProgramDAO.getPrograms(SQL_PROGRAMS.ALL, [], res)
    }

    /**
     * This is a function that will be called when the user request the `/create-program` endpoint. 
     * @param {Request} req - Request - The request object that contains the request parameters.
     * @param {Response} res - Response - The response object that is passed back to the client.
     */
    public postProgram = (req: Request, res: Response): void => {
        const { programName } = req.body
        ProgramDAO.postProgram(SQL_PROGRAMS.CONFIRM, SQL_PROGRAMS.CREATE, [programName], res)
    }
}


const programController = new ProgramController()
export default programController