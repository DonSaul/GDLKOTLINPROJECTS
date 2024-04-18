const express = require("express");
const router = express.Router();
const {
	createNote,
	findNotes,
	getNote,
	getNotes,
	updateNote,
	deleteNote,
	blockNote,
	completeNote,
	findNotesCategory
} = require("../controllers/note");

const jwtValidator = require("../middlewares/passport");
const validator = require("../middlewares/joi");
const {
	createNoteSchema,
    updateNoteSchema,
	paramsSchema,
} = require("../validations/note");



router.get("/allnotes", getNotes);
router.get("/notes",  findNotes);
router.get("/notes/:id", validator.params(paramsSchema), getNote);
router.get("/notes/category/:id", validator.params(paramsSchema), findNotesCategory);
router.post("/notes",  validator.body(createNoteSchema), createNote);

router.put(
	"/notes/:id",
	
	validator.params(paramsSchema),
	validator.body(updateNoteSchema),
	updateNote
);

router.delete(
	"/notes/:id",
	
	validator.params(paramsSchema),
	deleteNote
);

router.post(
	"/notes/complete/:id",
	
	validator.params(paramsSchema),
	completeNote
);

router.post(
	"/notes/block/:id",
	
	validator.params(paramsSchema),
	blockNote
);

module.exports = router;