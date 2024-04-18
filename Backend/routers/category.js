const express = require("express");
const router = express.Router();
const {
	createCategory,
	findCategory,
	getCategory,
	getCategories,
	updateCategory,
	deleteCategory
} = require("../controllers/category");

const jwtValidator = require("../middlewares/passport");
const validator = require("../middlewares/joi");
const {
	creatCategorySchema,
    updateCategorySchema,
	paramsSchema,
} = require("../validations/category");



router.get("/allcategories",jwtValidator, getCategories);
router.get("/category", jwtValidator, findCategory);
router.get("/category/:id",jwtValidator, validator.params(paramsSchema), getCategory);

router.post("/category", jwtValidator, validator.body(creatCategorySchema), createCategory);

router.put(
	"/category/:id",
	jwtValidator,
	validator.params(paramsSchema),
	validator.body(updateCategorySchema),
	updateCategory
);

router.delete(
	"/category/:id",
	jwtValidator,
	validator.params(paramsSchema),
	deleteCategory
);


module.exports = router;