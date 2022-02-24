import { Request, Response } from 'express'
import FacultyDAO from '../daos/faculty.dao'

import { SQL_FACULTY } from '../repositories/faculty.sql'


class FacultyController extends FacultyDAO {
    /**
     * This is a function that will be called when the user requests the `/faculties` endpoint. 
     */
    public getFaculties = (req: Request, res: Response): void => {
        FacultyDAO.getFaculties(SQL_FACULTY.ALL, [], res)
    }
}


const facultyController = new FacultyController()
export default facultyController