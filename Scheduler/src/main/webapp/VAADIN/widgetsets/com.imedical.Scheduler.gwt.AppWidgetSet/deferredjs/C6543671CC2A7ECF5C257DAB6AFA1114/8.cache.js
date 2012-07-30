function FS(){}
function AS(){}
function I$(){}
function w0(){}
function tob(){}
function sob(){}
function yYb(){}
function OYb(){}
function SYb(){}
function WYb(){}
function $Yb(){}
function PYb(b){this.b=b}
function TYb(b){this.b=b}
function XYb(b){$();this.b=b}
function MYb(b,c){b.enctype=c;b.encoding=c}
function Eub(b,c){b.onload=function(){c.gf()}}
function x0(){var b;this.zb=(b=$doc.createElement(Gfc),b.type=Qfc,b)}
function _Yb(b){var c;this.b=b;this.zb=(c=$doc.createElement(Gfc),c.type='file',c);this.zb[rfc]='gwt-FileUpload'}
function AYb(b){cwb(b.n,false);b.o||(b.e.zb[Eec]=true,undefined);b.d=false}
function BYb(b){cwb(b.n,true);b.e.zb[Eec]=false;b.d=true;if(b.o){zYb(b);b.o=false}}
function zYb(b){if(b.p){$doc.body.removeChild(b.p);b.p.onload=null;b.p=null}}
function EYb(b,c){if(b.f!=c){b.f=c;if(b.f){SW(b.e,1024);SW(b.e,2048)}}CW(b.zb,'v-upload-immediate',b.f)}
function DYb(b){sX(b.k,b.n);sX(b.k,b.e);b.e=new _Yb(b);b.e.zb.name=b.j+Mmc;b.e.zb[Eec]=!b.d;t_(b.k,b.e);t_(b.k,b.n);b.f&&SW(b.e,1024)}
function HS(){DS=new FS;Sb((Qb(),Pb),8);!!$stats&&$stats(vc(Lmc,Wdc,-1,-1));DS.yc();!!$stats&&$stats(vc(Lmc,Glc,-1,-1))}
function ES(){var b,c,d;while(BS){d=lb;BS=BS.b;!BS&&(CS=null);if(!d){(cob(),bob).Gf(_C,new tob);lgb()}else{try{(cob(),bob).Gf(_C,new tob);lgb()}catch(b){b=cG(b);if(Sp(b,38)){c=b;Ilb.ie(c)}else throw b}}}}
function FYb(b){if(b.e.zb.value.length==0||b.o||!b.d){Ilb.ke('Submit cancelled (disabled, no file or already submitted)');return}Sdb(b.b);b.c.submit();b.o=true;Ilb.ke('Submitted form');AYb(b);b.q=new XYb(b);bb(b.q,800)}
function CYb(b){var c;if(!b.p){c=$doc.createElement(Ofc);c.innerHTML="<iframe src=\"javascript:''\" name='"+b.j+"_TGT_FRAME' style='position:absolute;width:0;height:0;border:0'>"||Rdc;b.p=fe(c);$doc.body.appendChild(b.p);b.c.target=b.j+'_TGT_FRAME';Eub(b.p,b)}}
function GYb(){this.zb=$doc.createElement('form');this.e=new _Yb(this);this.k=new w_;this.g=new x0;this.c=this.zb;MYb(this.zb,Nmc);this.c.method='post';GZ(this,this.k);t_(this.k,this.g);t_(this.k,this.e);this.n=new ewb;JW(this.n,new PYb(this),(Gi(),Gi(),Fi));t_(this.k,this.n);this.zb[rfc]='v-upload';this.wb==-1?qU(this.zb,241|(this.zb.__eventBits||0)):(this.wb|=241)}
var Mmc='_file',Omc='buttoncaption',Lmc='runCallbacks8';_=FS.prototype=AS.prototype=new J;_.gC=function GS(){return gu};_.yc=function KS(){ES()};_.cM={};_=I$.prototype=new gW;_.gC=function J$(){return Xu};_.Dc=function K$(b){NW(this,b)};_.cM={10:1,13:1,15:1,22:1,72:1,73:1};_=x0.prototype=w0.prototype=new gW;_.gC=function y0(){return kv};_.cM={10:1,13:1,15:1,22:1,72:1,73:1};_=tob.prototype=sob.prototype=new J;_.te=function uob(){return new GYb};_.gC=function vob(){return ey};_.cM={140:1};_=GYb.prototype=yYb.prototype=new DZ;_.gC=function HYb(){return _C};_.Vc=function IYb(){MW(this);!!this.b&&CYb(this)};_.Dc=function JYb(b){(qV(b.type)&241)>0&&(hnb(this.b.C,b,this,null),undefined);NW(this,b)};_.Wc=function KYb(){OW(this);this.o||zYb(this)};_.gf=function LYb(){$mb((Ec(),Dc),new TYb(this))};_.Qd=function NYb(b,c){var d;if(_db(c,this,b,true)){return}if('notStarted' in b[1]){bb(this.q,400);return}if('forceSubmit' in b[1]){FYb(this);return}EYb(this,Boolean(b[1][ghc]));this.b=c;this.j=b[1][Kfc];this.i=b[1]['nextid'];d=Ydb(c,b[1][jhc][okc]);this.c.action=d;if(Omc in b[1]){this.n.c.textContent=b[1][Omc]||Rdc;this.n.zb.style.display=Rdc}else{this.n.zb.style.display=nec}this.e.zb.name=this.j+Mmc;if(Eec in b[1]||ehc in b[1]){AYb(this)}else if(!Boolean(b[1][yhc])){BYb(this);CYb(this)}};_.cM={10:1,13:1,15:1,17:1,19:1,20:1,21:1,22:1,26:1,34:1,72:1,73:1,91:1,93:1};_.b=null;_.c=null;_.d=true;_.f=false;_.i=0;_.j=null;_.n=null;_.o=false;_.p=null;_.q=null;_=PYb.prototype=OYb.prototype=new J;_.gC=function QYb(){return XC};_.Wb=function RYb(b){this.b.f?(this.b.e.zb.click(),undefined):FYb(this.b)};_.cM={12:1,40:1};_.b=null;_=TYb.prototype=SYb.prototype=new J;_.Ib=function UYb(){if(this.b.o){if(this.b.b){!!this.b.q&&ab(this.b.q);Ilb.ke('VUpload:Submit complete');Sdb(this.b.b)}DYb(this.b);this.b.o=false;BYb(this.b);this.b.vb||zYb(this.b)}};_.gC=function VYb(){return YC};_.cM={3:1,14:1};_.b=null;_=XYb.prototype=WYb.prototype=new Y;_.gC=function YYb(){return ZC};_.Eb=function ZYb(){Ilb.ke('Visiting server to see if upload started event changed UI.');sdb(this.b.b,this.b.j,'pollForStart',Rdc+this.b.i,true,105)};_.cM={68:1};_.b=null;_=_Yb.prototype=$Yb.prototype=new I$;_.gC=function aZb(){return $C};_.Dc=function bZb(b){NW(this,b);if(qV(b.type)==1024){this.b.f&&this.b.e.zb.value!=null&&!L5b(Rdc,this.b.e.zb.value)&&FYb(this.b)}else if((whb(),!vhb&&(vhb=new Qhb),whb(),vhb).b.i&&qV(b.type)==2048){this.b.e.zb.click();this.b.e.zb.blur()}};_.cM={10:1,13:1,15:1,22:1,72:1,73:1};_.b=null;var gu=r4b(rlc,'AsyncLoader8'),Xu=r4b(ulc,'FileUpload'),kv=r4b(ulc,'Hidden'),ey=r4b(Blc,'WidgetMapImpl$12$1'),XC=r4b(zlc,'VUpload$1'),YC=r4b(zlc,'VUpload$2'),ZC=r4b(zlc,'VUpload$3'),$C=r4b(zlc,'VUpload$MyFileUpload');Ndc(HS)();