'use strict'

import { Response } from 'express'
import { red } from 'colors'
import connectionDB from '../config/connection/connection_DB'


class SubjectDAO {
    protected static getSubjects = async (sqlQuery: string, parameters: any, res: Response): Promise<any> => {
        try {
            const { rows } = await connectionDB.pool.result(sqlQuery, parameters)
            return res.status(200).json({ ok: true, resultsQuery: rows })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el administrador' })
        }
    }

    protected static postSubject = async (sqlConfirm: string, sqlCreate: string, parameters: any, res: Response): Promise<any> => {
        try {
            const { subjectId, amount } = await connectionDB.pool.task(async query => {
                const { amount } = await query.one(sqlConfirm, parameters)
                if (amount === '0') {
                    return query.one(sqlCreate, parameters)
                } 
                else return { subjectId: 0, amount }
            })
            if (subjectId !== 0) {
                return res.status(201).json({ ok: true, msg: 'Materia creada', subjectId })
            }
            else return res.status(400).json({ ok: false, msg: 'Referencia de Materia ya existente', amount })
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el administrador' })
        }
    }
}


export default SubjectDAO