import { Router } from 'express'

import candidateController from '../controllers/candidate.controller'


class CandidateRoutes {
    public candidateRoutes: Router

    constructor() {
        this.candidateRoutes = Router()
        this.config()
    }

    /**
     * This is a method that is called when the class is instantiated. 
     */
    public config = (): void => {
        this.candidateRoutes.get('/', candidateController.getCandidates)
    }
}


const candidateRoutes = new CandidateRoutes()
export default candidateRoutes.candidateRoutes