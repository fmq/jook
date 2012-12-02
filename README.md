Jook is a very, very simple html template "engine" that is used in a Java container

For now here is a list of valid tags:

<tmpl:template src="templateName.html" />

The template tag must be used in conjuntion with the <tmpl:content name="" /> tag that defines the content that will be placed in the <tmpl:holder name=""/> tag (this tag is defined in the template).

The other tag is <tmpl:include src="" /> with is replaced with the content of the src file.


