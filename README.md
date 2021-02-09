# 4J_NLP

## Description
Javaを用いた自然言語処理のプログラムです.Wikipediaから抽出した記事のTerm Frequency,Inverse Document Frequency,TF-IDFの3つを計算します.さらに,計算前に次に示す文字列を除外します.

- 記号,助動詞,助詞,接続詞,動詞,連体詞,数字の除去
- URLの削除
- ストップワードの除去
- 大文字,小文字の統一

## Directory structure

```
.
├── 408_金澤雄大.zip : 提出したファイル
├── NaturalLanguageProcessing : メインプロジェクト
├── README.md
├── report
└── sjis2utf8.bat : sjisのファイルをutf8に変換するbatファイル
```