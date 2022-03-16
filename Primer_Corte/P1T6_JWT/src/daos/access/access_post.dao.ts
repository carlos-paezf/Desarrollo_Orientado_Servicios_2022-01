import jwt from 'jsonwebtoken'
import { red } from "colors";
import { Response } from "express";

import connectionDB from '../../config/connection/connection_DB';


export class AccessDAO_Post {
    protected static validateAccess = async (sqlGetAccess: string, params: any, res: Response): Promise<any> => {
        try {
            const data = await connectionDB.pool.oneOrNone(sqlGetAccess, params)
            return this.responseDao(data, res)
        } catch (error) {
            console.log(red('Error: '), error)
            return res.status(500).json({ ok: false, msg: 'Comuníquese con el Administrador' })
        }
    }

    protected static responseDao = async (data: any, res: Response): Promise<any> => {
        if (data !== null) {
            const token = jwt.sign(
                { userName: data.userName, userSurname: data.userSurname, role: data.roleName }, 
                'Firma Secreta', 
                { expiresIn: '1h' }
            )
            return res.status(200).json({
                ok: true,
                msg: 'Se ha generado un nuevo token de ingreso',
                data,
                token
            })
        }
        return res.status(401).json({ ok: false, msg: 'Correo o contraseña incorrectas' })
    }
}