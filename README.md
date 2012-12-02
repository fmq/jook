Jook is a very, very simple html template "engine" that is used in a Java container

For now here is a list of valid tags:

<table>
<tr>
<th>Tag</th>
<th>Attributes</th>
<th>Description</th>
</tr>
<td>tmpl:template</td>
<td>src</td>
<td>Defines the template name to be used to display the content of the file</td>
</tr>
</tr>
<td>tmpl:content</td>
<td>name</td>
<td>Defines the content to be included.</td>
</tr>
</tr>
<td>tmpl:holder</td>
<td>name</td>
<td>Tag that goes in the template and defines where the content will be placed.</td>
</tr>
</tr>
<td>tmpl:include</td>
<td>src, target: [header|body]</td>
<td>This tag will be replaced with the content of the file defined in the src attribute. If the target is "header" then the src will be included at the end of the < head > tag. Notice that for now there is a bug where anything placed in the header after the tmpl:include tag will be discarded.</td>
</tr>
</table>


