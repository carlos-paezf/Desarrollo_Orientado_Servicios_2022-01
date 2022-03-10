'use strict'

import { Router } from "express"
import { semesterControllerDelete, semesterControllerGet, semesterControllerPost, semesterControllerUpdate } from "../controllers/semesters"


class SemesterRoutes {
    public semesterRoutes: Router

    constructor() {
        this.semesterRoutes = Router()
        this.config()
    }

    /**
     * This is a method that is called when the class is instantiated.
     * It creates the routes for the semester controller. 
     */
    public config = (): void => {
        this.semesterRoutes.get('/', semesterControllerGet.getSemesters)
        this.semesterRoutes.get('/:semesterId', semesterControllerGet.getOneSemesterById)
        this.semesterRoutes.post('/create-semester', semesterControllerPost.postSemester)
        this.semesterRoutes.put('/update-semester/:semesterId', semesterControllerUpdate.updateOneSemesterById)
        this.semesterRoutes.delete('/:semesterId', semesterControllerDelete.deleteOneSemesterById)
    }
}


const semesterRoutes = new SemesterRoutes()
export default semesterRoutes.semesterRoutes