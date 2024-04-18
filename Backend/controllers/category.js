const { insert, findByUser,findById,findAll,blockById,completeById ,deleteById,update} = require("../services/category");

exports.createCategory = async function (request, response) {
	const { title, content,icon } = request.body;
	const { id } = request.user;

	const category = await insert({ title, content,icon, userId: id });

	response.status(201).json(category);
};

exports.findCategory = async function (request, response) {
	const { id } = request.user;

	const category = await findByUser(id);

	response.status(200).json(category);
};

exports.getCategory = async function (request, response) {
	const { id } = request.params;
	const category = await findById(id);
	response.status(200).json(category);
};

exports.getCategories = async function (request, response) {
	const categories = await findAll();
	response.status(200).json(categories);
};



exports.updateCategory = async function (request, response) {
	const { title, content,icon } = request.body;
	const { id } = request.params;

	await update(id, { title, content,icon });
	response.status(204).end();
};

exports.deleteCategory= async function (request, response) {
	const { id } = request.params;
	await deleteById(id);
	response.status(204).end();
};

