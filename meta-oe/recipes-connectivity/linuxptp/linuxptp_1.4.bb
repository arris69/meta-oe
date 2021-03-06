DESCRIPTION = "Precision Time Protocol (PTP) according to IEEE standard 1588 for Linux"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "virtual/kernel"

SRC_URI = "http://sourceforge.net/projects/linuxptp/files/v${PV}/linuxptp-${PV}.tgz"

SRC_URI[md5sum] = "a37ad2b2ef7d1ebc4d64a66d3fe55cdf"
SRC_URI[sha256sum] = "6cfd5291fb7394cc9f25458927874a203971b66b76d1c9d6568e007d0cbd81f2"

inherit autotools pkgconfig

EXTRA_OEMAKE = 'KBUILD_OUTPUT="${STAGING_KERNEL_DIR}" CROSS_COMPILE="${TARGET_PREFIX}"'

do_configure_append () {
    find ${S} -name makefile | xargs sed -i 's,^\(CC\|CFLAGS\|prefix\|AR\)=,\1 ?=,g'
}

do_install () {
    install -d ${D}/${bindir}
    install -p ${S}/ptp4l  ${D}/${bindir}
    install -p ${S}/pmc  ${D}/${bindir}
    install -p ${S}/phc2sys  ${D}/${bindir}
    install -p ${S}/hwstamp_ctl  ${D}/${bindir}
}
