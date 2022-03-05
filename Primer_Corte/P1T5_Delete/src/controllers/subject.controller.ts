'use strict'

import { Request, Response } from 'express'
import SubjectDAO from '../daos/subject.dao'
import { SQL_SUBJECT } from '../repositories/subject.sql';


class SubjectController extends SubjectDAO {
    public getSubjects = (req: Request, res: Response): void => {
        SubjectDAO.getSubjects(SQL_SUBJECT.ALL, [], res)
    }

    public postSubject = (req: Request, res: Response): void => {
        const { subjectName, subjectReference } = req.body
        const params = [subjectName, subjectReference]
        SubjectDAO.postSubject(SQL_SUBJECT.CONFIRM, SQL_SUBJECT.CREATE, params, res)
    }
}


const subjectController = new SubjectController()
export default subjectController