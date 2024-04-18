const Joi = require("joi");

exports.creatCategorySchema = Joi.object({
	title: Joi.string().min(5).max(100).required(),
	content: Joi.string().min(5).max(2500).required(),
	icon: Joi.required()
});

exports.updateCategorySchema = Joi.object({
	title: Joi.string().min(5).max(100).required(),
	content: Joi.string().min(5).max(2500).required(),
    icon: Joi.required()
}).min(1);

exports.paramsSchema = Joi.object({
	id: Joi.number().required(),
});