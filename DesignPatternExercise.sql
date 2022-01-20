--CREATE DATABASE DESIGN_PATTERN_EXERCISE;
GO 

USE DESIGN_PATTERN_EXERCISE;
GO

-- Table Categories  
CREATE TABLE Categories(
	id int PRIMARY KEY,
	[name] nvarchar(100) NOT NULL,
	[status] bit default 1
)
GO

-- Table Products
CREATE TABLE Products(
	id varchar(11) PRIMARY KEY,
	name nvarchar(150) NOT NULL,
	price float NOT NULL,
	[status] bit default 1,
	category_id int NOT NULL,
	FOREIGN KEY (category_id) REFERENCES Categories(id)
)

-- *** PROC Categories *** --
GO
-- Get all 
CREATE PROC Sp_selectAllCategories
AS
SELECT * FROM Categories ORDER BY name ASC
GO 

-- Search name 
CREATE PROC Sp_findCateByName(@name varchar(100))
AS
SELECT * FROM Categories WHERE [name] LIKE '%' + @name + '%'; 
GO 

-- Get detail
CREATE PROC Sp_findCategoryId(@id int)
AS
SELECT * FROM Categories WHERE id = @id
GO 

-- Add new
CREATE PROC Sp_addCategory(@id varchar(11), @name nvarchar(100), @status bit)
AS 
INSERT INTO Categories VALUES(@id, @name, @status)
GO

-- Update
CREATE PROC Sp_editCategory(@id int,@name varchar(100), @status bit)
AS 
UPDATE Categories SET [name] = @name, [status] = @status WHERE id = @id; 
GO

-- Delete
CREATE PROC Sp_removeCategory(@id int)
AS 
DELETE FROM Categories WHERE id = @id; 
GO

-- Get all id
CREATE PROC Sp_allIdCate
AS 
SELECT [id] FROM Categories;
GO

-- INSERT DEMO
INSERT INTO Categories(id, name) VALUES ('1', 'Trousers')
INSERT INTO Categories(id, name) VALUES ('2', 'Shirt')
INSERT INTO Categories(id, name) VALUES ('3', 'Shoes')
INSERT INTO Categories(id, name) VALUES ('4', 'Hat')

-- *** PROC Products *** --
GO
-- Get all 
CREATE PROC Sp_selectAllProducts
AS
SELECT Products.*, Categories.name as 'CategoryName' FROM Products JOIN Categories ON Products.category_id = Categories.id  ORDER BY id ASC; 
GO 

-- Search name
CREATE PROC Sp_findProductByName(@name varchar(100))
AS
SELECT * FROM Products WHERE [name] LIKE '%' + @name + '%'; 
GO 

-- Get detail
CREATE PROC Sp_findProductId(@id int)
AS
SELECT * FROM Products WHERE id = @id
GO 

-- Add new
CREATE PROC Sp_addProduct(@id varchar(11), @name nvarchar(100), @price float, @status bit, @categoryId int)
AS 
INSERT INTO Products VALUES(@id, @name, @price, @status, @categoryId)
GO

-- Update
CREATE PROC Sp_editProduct(@id varchar(11), @name nvarchar(100), @price float, @status bit, @categoryId int)
AS 
UPDATE Products SET [name] = @name, price = @price, [status] = @status, category_id = @categoryId WHERE id = @id; 
GO

-- Delete
CREATE PROC Sp_removeProduct(@id int)
AS 
DELETE FROM Products WHERE id = @id; 
GO

-- Get all id
CREATE PROC Sp_allIdProduct
AS 
SELECT [id] FROM Products;
GO

-- Check forein key
CREATE PROC Sp_getProductByCateId(@id int)
AS 
SELECT * FROM Products WHERE category_id = @id
GO

-- INSERT DEMO
INSERT INTO Products VALUES (1, 'T-Shirt China', 130000, 1, 2)
INSERT INTO Products VALUES (2, 'Jacket Korea', 240300,  1, 1)
INSERT INTO Products VALUES (3, 'Jean France', 32000, 0, 1)
INSERT INTO Products VALUES (4, 'Hat India', 4200, 1, 4)
INSERT INTO Products VALUES (5, 'Adidas', 12000, 1, 3)
