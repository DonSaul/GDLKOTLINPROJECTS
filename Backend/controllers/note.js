const { insert, findByUser,findById,findAll,blockById,completeById ,deleteById,update,findByCategory} = require("../services/note");

exports.createNote = async function (request, response) {
	const { title, content } = request.body;

	const note = await insert({ title, content });

	response.status(201).json(note);
};

exports.findNotes = async function (request, response) {
	
	const notes = await findAll();

	response.status(200).json(notes);
};

exports.findNotesCategory = async function (request, response) {
	const { id } = request.params;

	const notes = await findByCategory(id);

	response.status(200).json(notes);
};

exports.getNote = async function (request, response) {
	const { id } = request.params;
	const note = await findById(id);
	response.status(200).json(note);
};

exports.getNotes = async function (request, response) {
	const notes = await findAll();
	response.status(200).json(notes);
};



exports.updateNote = async function (request, response) {
	const { title, content } = request.body;
	const { id } = request.params;

	await update(id, { title, content });
	response.status(204).end();
};

exports.deleteNote = async function (request, response) {
	const { id } = request.params;
	await deleteById(id);
	response.status(204).end();
};

exports.blockNote = async function (request, response) {
	const { id } = request.params;
	await blockById(id);
	response.status(204).end();
};

exports.completeNote = async function (request, response) {
	const { id } = request.params;
	await completeById(id);
	response.status(204).end();
};