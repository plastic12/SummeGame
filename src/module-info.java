module main{
	exports main;
	requires transitive javafx.graphics;
	requires javafx.controls;
	requires transitive json.simple;
	opens data;
	opens texture;
}