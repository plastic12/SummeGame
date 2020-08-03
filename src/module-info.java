module main{
	exports main;
	requires transitive javafx.graphics;
	requires javafx.controls;
	requires transitive json.simple;
	requires javafx.base;
	opens data;
	opens texture;
}