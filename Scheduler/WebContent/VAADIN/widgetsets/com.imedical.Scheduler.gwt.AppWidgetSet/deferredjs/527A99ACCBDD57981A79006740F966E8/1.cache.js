function vI(){}
function qI(){}
function Rxb(){}
function Qxb(){}
function rNb(){}
function PNb(){}
function UNb(){}
function dOb(){}
function yNb(b,c){return eub(b.e,b,c)}
function E$(b,c,d,e){C$(b,c);b.fd(c,d,e)}
function Blc(b){Xgc(this);Jgc(this,b)}
function eOb(b){this.b=new Lmc;this.c=b}
function QNb(b,c){this.b=b;this.i=c;this.c=xuc}
function bOb(b,c){this.n=b;this.j=c[1][Jvc];this.e=c[1][Kvc];_Nb(this,c)}
function B$(b,c){var d;TZ(c);d=b.tb.d;b.fd(c,0,0);u$(b,c,b.Ab,d,true)}
function XNb(b){var c;if(b.c){c=b.c.u.Ee()+mbc(b.c);return c}else{return 0}}
function $Nb(b){if(!b.d){return false}if(b.i){return false}else{ZNb(b);return true}}
function ANb(b){var c,d;for(d=Kmc(b,0);d.c!=d.e.b;){c=hq(Rmc(d),102);ZNb(c)}}
function BNb(b){var c,d;for(d=Kmc(b,0);d.c!=d.e.b;){c=hq(Rmc(d),102);if(!c.g){ZNb(c);Smc(d)}}}
function Jgc(b,c){var d,e;for(e=c.Jf().ed();e.Cc();){d=hq(e.Dc(),62);b.Lf(d.Rf(),d.Sf())}}
function VNb(b){var c,d;c=b.n.u[b.j];for(d=1;d<b.k;++d){c+=b.n.y+b.n.u[b.j+d]}return c}
function WNb(b){var c,d;d=b.n.i[b.e];for(c=1;c<b.f;++c){d+=b.n.x+b.n.i[b.e+c]}return d}
function FNb(b){var c,d;c=Zp(UG,{30:1,32:1},-1,b.length,1);for(d=0;d<c.length;++d){c[d]=b[d]}return c}
function xNb(b,c){var d,e,f;f=c[1][Jvc];e=c[1][Kvc];d=b.c[e][f];if(!d){d=new bOb(b,c);b.c[e][f]=d}else{_Nb(d,c)}return d}
function YNb(b,c,d){if(!!b.c&&b.c.vb){E$(b.n.b,b.c,c,d);rbc(b.c,WNb(b),VNb(b));b.c.b=new $Bb(b.b);wbc(b.c,WNb(b),VNb(b))}}
function C$(b,c){if(c.zb!=b){throw new Aec('Widget must be a child of this panel.')}}
function G$(){H$.call(this,$doc.createElement(Mpc));this.Ab.style[hoc]=Eqc;this.Ab.style[yqc]=Qpc}
function F$(b,c,d){var e;e=b.Ab;if(c==-1&&d==-1){J$(e)}else{e.style[hoc]=koc;e.style[ypc]=c+ppc;e.style[zpc]=d+ppc}}
function xI(){tI=new vI;Wb((Ub(),Tb),1);!!$stats&&$stats(zc(Hvc,Wnc,-1,-1));tI.Ac();!!$stats&&$stats(zc(Hvc,Ivc,-1,-1))}
function $p(b,c,d,e,f,g,i){var k,n,o,p;o=e[f];n=f==g-1;p=Xp(n?i:0,o);dq();fq(p,bq,cq);p.aC=b[f];p.cM=c[f];p.qI=d[f];if(!n){++f;for(k=0;k<o;++k){p[k]=$p(b,c,d,e,f,g,i)}}return p}
function uI(){var b,c,d;while(rI){d=pb;rI=rI.b;!rI&&(sI=null);if(!d){(Mxb(),Lxb).Lf($B,new Rxb);Vpb()}else{try{(Mxb(),Lxb).Lf($B,new Rxb);Vpb()}catch(b){b=CH(b);if(jq(b,38)){c=b;qvb.xe(c)}else throw b}}}}
function aOb(b,c){if(!!c&&!Boolean(c[1][Hrc])){xpc in c[1]&&c[1][xpc].indexOf(Crc)!=-1?(b.g=true):(b.g=false);if(spc in c[1]){b.o=b.i=c[1][spc].indexOf(Crc)!=-1;xpc in c[1]&&(b.o=false)}else{b.o=!(xpc in c[1]);b.i=false}}}
function CNb(b,c){var d,e,f,g;d=null;for(f=Kmc(b.g,0);f.c!=f.e.b;){e=hq(Rmc(f),178);if(e.c<c.f){continue}else{d=e;break}}if(!d){d=new eOb(c.f);Imc(b.g,d)}else if(d.c!=c.f){g=new eOb(c.f);Zic(b.g,_hc(b.g,d),g);d=g}Gmc(d.b,c)}
function DNb(b,c){var d,e,f,g;d=null;for(f=Kmc(b.v,0);f.c!=f.e.b;){e=hq(Rmc(f),178);if(e.c<c.k){continue}else{d=e;break}}if(!d){d=new eOb(c.k);Imc(b.v,d)}else if(d.c!=c.k){g=new eOb(c.k);Zic(b.v,_hc(b.v,d),g);d=g}Gmc(d.b,c)}
function ENb(){this.Ab=$doc.createElement(Mpc);this.k=$doc.createElement(Mpc);this.b=new G$;this.z=new zlc;this.r=new zlc;this.d=new QNb(this,this);this.g=new Lmc;this.v=new Lmc;this.Ab.appendChild(this.k);this.Ab[qpc]='v-gridlayout';M0(this,this.b)}
function sNb(b){var c,d,e;for(d=0;d<b.c.length;++d){for(e=0;e<b.c[d].length;++e){c=b.c[d][e];if(c){if(!!c.c&&c.o){c.c.Ab.style[spc]=WNb(c)+ppc;Abc(c.c)}c.k==1?!c.g&&b.u[e]<(c.c?c.c.u.De()+jbc(c.c):0)&&(b.u[e]=c.c?c.c.u.De()+jbc(c.c):0):DNb(b,c)}}}uNb(b);b.p=FNb(b.u)}
function wNb(b){var c,d,e,f,g,i,k;if(!Bfc(Rnc,b.j)){k=b.p[0];for(i=1;i<b.p.length;++i){k+=b.y+b.p[i]}c=(parseInt(b.Ab[vpc])||0)-b.n;g=c-k;e=0;if(g>0){for(i=0;i<b.u.length;++i){f=~~(g*b.t[i]/1000);b.u[i]=b.p[i]+f;e+=f}g-=e;d=0;while(g>0){++b.u[d%b.u.length];--g;++d}}}}
function vNb(b){var c,d,e,f,g,i,k;if(!Bfc(Rnc,b.A)){k=b.o[0];for(i=1;i<b.o.length;++i){k+=b.x+b.o[i]}b.b.Ab.style[spc]=Rnc;c=parseInt(b.b.Ab[wpc])||0;g=c-k;e=0;if(g>0){for(i=0;i<b.i.length;++i){f=~~(g*b.f[i]/1000);b.i[i]=b.o[i]+f;e+=f}g-=e;d=0;while(g>0){++b.i[d%b.i.length];--g;++d}}}}
function tNb(b){var c,d,e,f,g,i,k,n,o,p;for(k=Kmc(b.g,0);k.c!=k.e.b;){i=hq(Rmc(k),178);for(e=Kmc(i.b,0);e.c!=e.e.b;){d=hq(Rmc(e),102);p=d.i?0:XNb(d);c=b.i[d.e];for(g=1;g<d.f;++g){c+=b.x+b.i[d.e+g]}if(c<p){n=p-c;o=~~(n/d.f);for(g=0;g<d.f;++g){f=d.e+g;b.i[f]+=o;n-=o}if(n>0){for(g=0;g<d.f;++g){f=d.e+g;b.i[f]+=1;n-=1;if(n==0){break}}}}}}}
function uNb(b){var c,d,e,f,g,i,k,n,o,p;for(k=Kmc(b.v,0);k.c!=k.e.b;){i=hq(Rmc(k),178);for(e=Kmc(i.b,0);e.c!=e.e.b;){d=hq(Rmc(e),102);f=d.g?0:d.c?d.c.u.De()+jbc(d.c):0;c=b.u[d.j];for(g=1;g<d.k;++g){c+=b.y+b.u[d.j+g]}if(c<f){n=f-c;p=~~(n/d.k);for(g=0;g<d.k;++g){o=d.j+g;b.u[o]+=p;n-=p}if(n>0){for(g=0;g<d.k;++g){o=d.j+g;b.u[o]+=1;n-=1;if(n==0){break}}}}}}}
function zNb(b){var c,d,e,f,g,i;g=0;i=0;for(e=0;e<b.c.length;++e){i=0;for(f=0;f<b.c[e].length;++f){d=b.c[e][f];!!d&&YNb(d,g,i);i+=b.u[f]+b.y}g+=b.i[e]+b.x}Bfc(Rnc,b.A)?(b.b.Ab.style[spc]=g-b.x+ppc,undefined):(b.b.Ab.style[spc]=Rnc,undefined);if(Bfc(Rnc,b.j)){c=i-b.y}else{c=(parseInt(b.Ab[vpc])||0)-b.n;c<0&&(c=0)}b.b.Ab.style[xpc]=c+ppc}
function ZNb(b){var c;c=inb(b.n.e,b.d);if(!b.c||b.c.s!=c){if(b.n.z.If(c)){b.c=hq(b.n.z.Kf(c),177);b.c.Ab.style[spc]=Rnc;b.c.Ab.style[xpc]=Rnc;ubc(b.c,hq(c,76))}else{b.c=new Cbc(hq(c,76),0);b.n.z.Lf(hq(c,76),b.c);b.c.Ab.style[spc]=Rnc;B$(b.n.b,b.c)}b.n.r.Lf(c,b)}pbc(b.c,b.d,b.n.e,-1);b.n.w&&(Rtb(),Boolean(b.d[1][Hrc]))&&nnb(b.n.e,b.c.s);Abc(b.c);b.n.q.Mf(c)}
function _Nb(b,c){var d,e,f;b.f=Lvc in c[1]?c[1][Lvc]:1;b.k=Mvc in c[1]?c[1][Mvc]:1;for(d=0;d<b.f;++d){for(e=0;e<b.k;++e){(d>0||e>0)&&(b.n.c[b.e+d][b.j+e]=null)}}c=c[2];if(b.d){if(!c){b.c=null}else if(!!b.c&&b.c.s!=inb(b.n.e,c)){b.c=null;f=inb(b.n.e,c);if(b.n.z.If(f)){b.c=hq(b.n.z.Kf(f),177);b.c.Ab.style[spc]=Rnc;b.c.Ab.style[xpc]=Rnc;b.n.r.Lf(f,b)}}}b.d=c;aOb(b,c)}
var Ovc='VGridLayout$Cell;',Hvc='runCallbacks1',Kvc='x',Jvc='y';_=vI.prototype=qI.prototype=new J;_.gC=function wI(){return ht};_.Ac=function AI(){uI()};_.cM={};_=G$.prototype=gZ.prototype;_.fd=function M$(b,c,d){F$(b,c,d)};_=b8.prototype;_.fd=function e8(b,c,d){c-=ye($doc);d-=ze($doc);F$(b,c,d)};_=Rxb.prototype=Qxb.prototype=new J;_.Ie=function Sxb(){return new ENb};_.gC=function Txb(){return wz};_.cM={161:1};_=ENb.prototype=rNb.prototype=new J0;_.ee=function GNb(b){var c;c=hq(this.r.Kf(b),102);return new otb(WNb(c)-mbc(c.c),VNb(c)-jbc(c.c))};_.gC=function HNb(){return $B};_.qd=function INb(){return this.k};_.fe=function JNb(b,c){var d;d=hq(this.z.Mf(b),177);if(!d){return}ubc(d,c);this.z.Lf(c,d);this.r.Lf(hq(c,26),hq(this.r.Kf(b),102))};_.ge=function KNb(b){var c,d,e,f,g,i,k,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B;r=false;w=false;x=false;s=parseInt(this.b.Ab[vpc])||0;t=parseInt(this.b.Ab[wpc])||0;(Bfc(Rnc,this.A)||Bfc(Rnc,this.j))&&(r=true);i=new rjc;k=new rjc;for(v=b.ed();v.Cc();){u=hq(v.Dc(),26);d=hq(this.r.Kf(u),102);if(!d.g||!d.i){d.c.Ab.style[spc]=Rnc;d.c.Ab.style[xpc]=Rnc;Abc(d.c);ybc(d.c);B=XNb(d);c=this.i[d.e];for(p=1;p<d.f;++p){c+=this.x+this.i[d.e+p]}if(c<B){r=true;d.f==1?(this.i[d.e]=this.o[d.e]=B):(w=true)}else c!=B&&gjc(i,Wec(d.e));o=d.c?d.c.u.De()+jbc(d.c):0;c=this.u[d.j];for(p=1;p<d.k;++p){c+=this.y+this.u[d.j+p]}if(c<o){r=true;d.k==1?(this.u[d.j]=this.p[d.j]=o):(x=true)}else c!=o&&gjc(k,Wec(d.j))}}if(i.c>0){for(f=new qic(i);f.c<f.e.Ff();){e=hq(oic(f),110);g=0;for(p=0;p<this.u.length;++p){d=this.c[e.b][p];if(!!d&&!!d.d&&!d.i&&d.f==1){B=XNb(d);B>g&&(g=B)}}this.o[e.b]=g}r=true;this.i=FNb(this.o);tNb(this);w=false}w&&tNb(this);if(k.c>0){r=true;for(A=new qic(k);A.c<A.e.Ff();){z=hq(oic(A),110);y=this.p[z.b]=0;for(p=0;p<this.i.length;++p){d=this.c[p][z.b];if(!!d&&!!d.d&&!d.g&&d.k==1){n=d.c?d.c.u.De()+jbc(d.c):0;n>y&&(y=n)}}this.p[z.b]=y}this.u=FNb(this.p);uNb(this);x=false}x&&uNb(this);if(r){vNb(this);wNb(this);zNb(this);for(p=0;p<this.c.length;++p){for(q=0;q<this.c[p].length;++q){d=this.c[p][q];!!d&&!!d.c&&(d.g||d.i)&&nnb(this.e,d.c.s)}}}return !((parseInt(this.b.Ab[vpc])||0)!=s||(parseInt(this.b.Ab[wpc])||0)!=t)};_.Qc=function LNb(b){var c,d,e,f;this.Ab.style[xpc]=b;if(!Bfc(b,this.j)){this.j=b;if(this.s){this.w=true}else{wNb(this);zNb(this);for(d=(e=Igc(this.r).c.ed(),new Hic(e));d.b.Cc();){c=hq((f=hq(d.b.Dc(),62),f.Rf()),26);nnb(this.e,hq(c,76))}}}};_.Tc=function MNb(b){var c,d,e,f,g,i,k,n,o,p,q,r,s,t,u,v,w,x,y,z;this.Ab.style[spc]=b;if(!Bfc(b,this.A)){this.A=b;if(this.s){this.w=true}else{s=FNb(this.i);vNb(this);k=false;i=null;for(n=0;n<s.length;++n){if(this.i[n]!=s[n]){g=this.c[n];for(o=0;o<g.length;++o){c=g[o];if(!!c&&!!c.c&&c.o){rbc(c.c,WNb(c),VNb(c));nnb(this.e,c.c.s);Abc(c.c);p=c.c?c.c.u.De()+jbc(c.c):0;if(this.i[n]<s[n]&&p>this.p[o]&&c.k==1){this.p[o]=p;if(p>this.u[o]){this.u[o]=p;k=true}}else if(p<this.p[o]){!i&&(i=new Hlc);Glc(i,Wec(o))}}}}}if(i){v=false;for(u=(w=Igc(i.b).c.ed(),new Hic(w));u.b.Cc();){t=hq((x=hq(u.b.Dc(),62),x.Rf()),110);r=this.p[t.b];q=0;for(f=0;f<this.i.length;++f){e=this.c[f][t.b];!!e&&!e.g&&(e.c?e.c.u.De()+jbc(e.c):0)>q&&(q=e.c?e.c.u.De()+jbc(e.c):0)}if(q<r){this.p[t.b]=this.u[t.b]=q;v=true}}if(v){uNb(this);this.p=FNb(this.u);k=true}}zNb(this);for(d=(y=Igc(this.r).c.ed(),new Hic(y));d.b.Cc();){c=hq((z=hq(d.b.Dc(),62),z.Rf()),26);nnb(this.e,hq(c,76))}k&&Bfc(Rnc,this.j)&&qub(this,false)}}};_.he=function NNb(b,c){var d;d=hq(this.z.Kf(b),177);!!d&&xbc(d,c,this.e);if(!this.s){aOb(hq(this.r.Kf(b),102),c);gjc(this.e.j,b)}};_.Ud=function ONb(b,c){var d,e,f,g,i,k,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F;this.s=true;this.e=c;if(Jnb(c,this,b,true)){this.s=false;return}dCb(this.d,c);this.b.Ab.style[spc]=sqc;z=new ZPb(b[1][Vsc]);A='v-gridlayout-margin';(z.b&1)==1&&(A+=' v-gridlayout-margin-top');(z.b&2)==2&&(A+=' v-gridlayout-margin-right');(z.b&4)==4&&(A+=' v-gridlayout-margin-bottom');(z.b&8)==8&&(A+=' v-gridlayout-margin-left');this.k.className=A;this.n=(this.k.offsetHeight||0)-(parseInt(this.b.Ab[vpc])||0);B=$doc.createElement(Mpc);B.className='v-gridlayout-spacing-'+(Boolean(b[1][Wsc])?Hpc:arc);B.style[spc]=Woc;B.style[xpc]=Woc;this.b.Ab.appendChild(B);this.x=B.offsetWidth||0;this.y=B.offsetHeight||0;this.b.Ab.removeChild(B);n=b[1][Lvc];v=b[1][Mvc];this.i=Zp(UG,{30:1,32:1},-1,n,1);this.u=Zp(UG,{30:1,32:1},-1,v,1);if(this.c==null){this.c=$p([AH,mH],[{30:1,32:1,36:1},{30:1,32:1,35:1,36:1}],[35,102],[n,v],0,2,0)}else if(this.c.length!=n||this.c[0].length!=v){q=$p([AH,mH],[{30:1,32:1,36:1},{30:1,32:1,35:1,36:1}],[35,102],[n,v],0,2,0);for(o=0;o<this.c.length;++o){for(p=0;p<this.c[o].length;++p){o<n&&p<v&&(q[o][p]=this.c[o][p])}}this.c=q}this.q=new Blc(this.z);e=Hxb(b[1],Nvc);d=0;r=new Lmc;t=new Lmc;for(o=new Htb(b);C=o.c.length-2,C>o.b+1;){s=iq(Gtb(o));if(Bfc('gr',s[0])){for(p=new Htb(s);D=p.c.length-2,D>p.b+1;){f=iq(Gtb(p));if(Bfc('gc',f[0])){g=xNb(this,f);if(g.d){u=$Nb(g);g.b=e[d++];u||(new cnc(g,r.b),++r.c,true);g.f>1?CNb(this,g):u&&this.i[g.e]<XNb(g)&&(this.i[g.e]=XNb(g));g.g&&(new cnc(g,t.b),++t.c,true)}}}}}tNb(this);this.f=Hxb(b[1],'colExpand');this.t=Hxb(b[1],'rowExpand');this.o=FNb(this.i);vNb(this);BNb(r);sNb(this);wNb(this);ANb(r);for(i=Kmc(t,0);i.c!=i.e.b;){g=hq(Rmc(i),102);y=g.c.s;mnb(c,c.p[y.Ab.tkPid]);Abc(g.c)}zNb(this);for(x=(E=Igc(this.q).c.ed(),new Hic(E));x.b.Cc();){w=hq((F=hq(x.b.Dc(),62),F.Rf()),76);k=hq(this.z.Kf(w),177);this.r.Mf(w);this.z.Mf(w);TZ(k);Inb(c,hq(w,26))}this.q=null;this.s=false;this.w=false};_.cM={10:1,13:1,15:1,17:1,19:1,20:1,21:1,22:1,24:1,26:1,34:1,75:1,76:1,95:1,97:1};_.c=null;_.e=null;_.f=null;_.i=null;_.j=null;_.n=0;_.o=null;_.p=null;_.q=null;_.s=false;_.t=null;_.u=null;_.w=false;_.x=0;_.y=0;_.A=null;_=QNb.prototype=PNb.prototype=new YCb;_.Qe=function RNb(b){return yNb(this.b,b)};_.gC=function SNb(){return XB};_.Pe=function TNb(b,c){return NZ(this.b,b,c)};_.cM={12:1,41:1,43:1,51:1};_.b=null;_=bOb.prototype=UNb.prototype=new J;_.gC=function cOb(){return YB};_.cM={102:1};_.b=0;_.c=null;_.d=null;_.e=0;_.f=1;_.g=false;_.i=false;_.j=0;_.k=1;_.n=null;_.o=false;_=eOb.prototype=dOb.prototype=new J;_.gC=function fOb(){return ZB};_.cM={178:1};_.c=0;_=Blc.prototype=ylc.prototype;var ht=Wdc(svc,'AsyncLoader1'),wz=Wdc(Dvc,'WidgetMapImpl$1$1'),YB=Wdc(Bvc,'VGridLayout$Cell'),mH=Vdc(Fvc,Ovc),AH=Vdc('[[Lcom.vaadin.terminal.gwt.client.ui.',Ovc),XB=Wdc(Bvc,'VGridLayout$1'),ZB=Wdc(Bvc,'VGridLayout$SpanList');Nnc(xI)();