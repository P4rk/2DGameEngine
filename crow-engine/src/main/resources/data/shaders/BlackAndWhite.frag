//define our sampler2D object, i.e. texture
uniform sampler2D tex0;

void main() {
    vec4 color = texture2D(tex0, gl_TexCoord[0].st);
    float gray = dot(color.rgb, vec3(0.299, 0.587, 0.114));
    gl_FragColor = vec4(gray, gray, gray, color.a);
} 