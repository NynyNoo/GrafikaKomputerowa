let canvas = document.getElementById("glcanvas");

const scene = new THREE.Scene({color: 0xfff});

const camera = new THREE.PerspectiveCamera(100, window.innerWidth / window.innerHeight, 1, 1000);

const renderer = new THREE.WebGLRenderer({antialias: true, alpha: true});

renderer.setSize(window.innerWidth, window.innerHeight);

document.body.appendChild(renderer.domElement);

var light;
light = new THREE.DirectionalLight();
light.position.set(-6, 3, 4);
camera.add(light);
scene.add(camera);

const material = new THREE.MeshPhongMaterial(
    {
        color: 0x111111,

    });


const podstawadolkolor = new THREE.Mesh(
    new THREE.CylinderGeometry(1, 1, 0.15, 100), material);;
const podstawagorakolor = new THREE.Mesh
(new THREE.CylinderGeometry(0.9, 0.9, 0.25, 100), material);
podstawagorakolor.position.y = 0.1;

let CalaPodstawa = new THREE.Group();
CalaPodstawa.add(podstawadolkolor);
CalaPodstawa.add(podstawagorakolor);
const slup2 = new THREE.Mesh(
    new THREE.CylinderGeometry(0.4, 0.9, 2,100), material);
slup2.position.set(0, 1, 0);
CalaPodstawa.add(slup2);

const przerwa = new THREE.Mesh(
    new THREE.CylinderGeometry(0.70, 0.8, 0.15, 100), material);
przerwa.position.y=1.8


const ballGeometry = new THREE.SphereGeometry(Math.PI/5.5, 100, 100, Math.PI,  2*Math.PI, 0,  Math.PI);
material.side = THREE.DoubleSide;
const kula = new THREE.Mesh(ballGeometry, material);

kula.position.set(0, 2.3, 0);


CalaPodstawa.add(kula);
CalaPodstawa.add(przerwa);
CalaPodstawa.position.set(0, -4, 0);
CalaPodstawa.scale.set(3, 4, 1);
scene.add(CalaPodstawa);
/////////////////////////////////////////


camera.position.z =10;
renderer.render(scene, camera);

