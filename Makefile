# 図書管理システム Makefile

# 変数定義
JAVA = java
JAVAC = javac
CP = ".:lib/h2-2.4.240.jar:junit-4.13.2.jar:hamcrest-core-1.3.jar"
H2_JAR = lib/h2-2.4.240.jar
DB_URL = jdbc:h2:file:./library
DB_USER = sa
DB_PASS = ""

.PHONY: all compile init run test clean

# 全て実行（コンパイルとDB初期化）
all: compile init

# Javaファイルのコンパイル
compile:
	$(JAVAC) -cp $(CP) *.java

# データベースの初期化（SQLファイルからDBファイルを生成）
init:
	@echo "データベースを初期化しています..."
	rm -f library.mv.db library.trace.db
	$(JAVA) -cp $(H2_JAR) org.h2.tools.RunScript -url "$(DB_URL)" -user $(DB_USER) -password $(DB_PASS) -script "schema.sql"
	$(JAVA) -cp $(H2_JAR) org.h2.tools.RunScript -url "$(DB_URL)" -user $(DB_USER) -password $(DB_PASS) -script "data.sql"
	@echo "初期化が完了しました (library.mv.db が作成されました)"

# メインプログラムの実行
run:
	$(JAVA) -cp $(CP) Library

# JUnitテストの実行
test:
	$(JAVA) -cp $(CP) org.junit.runner.JUnitCore AvailableStateTest LoanedStateTest BookTest

# クラスファイルとDBファイルの削除
clean:
	rm -f *.class
	rm -f library.mv.db library.trace.db
