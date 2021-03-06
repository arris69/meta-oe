# Released under the MIT license (see COPYING.MIT for the terms)

# Applications using this library needs to add link against libbreakpad_client.a.

SUMMARY = "An open-source multi-platform crash reporting system"
DESCRIPTION = "Breakpad is a library and tool suite that allows you to distribute an application to users with compiler-provided debugging information removed, record crashes in compact \"minidump\" files, send them back to your server, and produce C and C++ stack traces from these minidumps. "
HOMEPAGE = "https://code.google.com/p/google-breakpad/"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=c80d1a3b623f72bb85a4c75b556551df"
SECTION = "libs"

inherit autotools

BBCLASSEXTEND = "native"

SRCREV = "r1218"
SRC_URI = "svn://google-breakpad.googlecode.com/svn;module=trunk;protocol=http"
S = "${WORKDIR}/trunk"

do_install_append() {
        install -d ${D}${includedir}
        install -d ${D}${includedir}/breakpad

        install -d ${D}${includedir}/breakpad/client/linux/crash_generation
        install -m 0644 ${S}/src/client/linux/crash_generation/crash_generation_client.h  ${D}${includedir}/breakpad/client/linux/crash_generation/crash_generation_client.h

        install -d ${D}${includedir}/breakpad/client/linux/handler/
        install -m 0644 ${S}/src/client/linux/handler/exception_handler.h ${D}${includedir}/breakpad/client/linux/handler/exception_handler.h
        install -m 0644 ${S}/src/client/linux/handler/minidump_descriptor.h ${D}${includedir}/breakpad/client/linux/handler/minidump_descriptor.h

        install -d ${D}${includedir}/breakpad/client/linux/minidump_writer
        install -m 0644 ${S}/src/client/linux/minidump_writer/linux_dumper.h ${D}${includedir}/breakpad/client/linux/minidump_writer/linux_dumper.h
        install -m 0644 ${S}/src/client/linux/minidump_writer/minidump_writer.h ${D}${includedir}/breakpad/client/linux/minidump_writer/minidump_writer.h

        install -d ${D}${includedir}/breakpad/common
        install -m 0644 ${S}/src/common/memory.h ${D}${includedir}/breakpad/common/memory.h
        install -m 0644 ${S}/src/common/scoped_ptr.h ${D}${includedir}/breakpad/common/scoped_ptr.h
        install -m 0644 ${S}/src/common/using_std_string.h ${D}${includedir}/breakpad/common/using_std_string.h

        install -d ${D}${includedir}/breakpad/google_breakpad/common
        install -m 0644 ${S}/src/google_breakpad/common/breakpad_types.h ${D}${includedir}/breakpad/google_breakpad/common/breakpad_types.h
        install -m 0644 ${S}/src/google_breakpad/common/minidump_format.h ${D}${includedir}/breakpad/google_breakpad/common/minidump_format.h
        install -m 0644 ${S}/src/google_breakpad/common/minidump_cpu_amd64.h ${D}${includedir}/breakpad/google_breakpad/common/minidump_cpu_amd64.h
        install -m 0644 ${S}/src/google_breakpad/common/minidump_cpu_arm.h ${D}${includedir}/breakpad/google_breakpad/common/minidump_cpu_arm.h
        install -m 0644 ${S}/src/google_breakpad/common/minidump_cpu_mips.h ${D}${includedir}/breakpad/google_breakpad/common/minidump_cpu_mips.h
        install -m 0644 ${S}/src/google_breakpad/common/minidump_cpu_ppc64.h ${D}${includedir}/breakpad/google_breakpad/common/minidump_cpu_ppc64.h
        install -m 0644 ${S}/src/google_breakpad/common/minidump_cpu_ppc.h ${D}${includedir}/breakpad/google_breakpad/common/minidump_cpu_ppc.h
        install -m 0644 ${S}/src/google_breakpad/common/minidump_cpu_sparc.h ${D}${includedir}/breakpad/google_breakpad/common/minidump_cpu_sparc.h
        install -m 0644 ${S}/src/google_breakpad/common/minidump_cpu_x86.h ${D}${includedir}/breakpad/google_breakpad/common/minidump_cpu_x86.h
        install -m 0644 ${S}/src/google_breakpad/common/minidump_exception_linux.h ${D}${includedir}/breakpad/google_breakpad/common/minidump_exception_linux.h
        install -m 0644 ${S}/src/google_breakpad/common/minidump_exception_mac.h ${D}${includedir}/breakpad/google_breakpad/common/minidump_exception_mac.h
        install -m 0644 ${S}/src/google_breakpad/common/minidump_exception_ps3.h ${D}${includedir}/breakpad/google_breakpad/common/minidump_exception_ps3.h
        install -m 0644 ${S}/src/google_breakpad/common/minidump_exception_solaris.h ${D}${includedir}/breakpad/google_breakpad/common/minidump_exception_solaris.h
        install -m 0644 ${S}/src/google_breakpad/common/minidump_exception_win32.h ${D}${includedir}/breakpad/google_breakpad/common/minidump_exception_win32.h

        install -d ${D}${includedir}/breakpad/third_party/lss
        install -m 0644 ${S}/src/third_party/lss/linux_syscall_support.h ${D}${includedir}/breakpad/third_party/lss/linux_syscall_support.h
}

PACKAGES =+ "${PN}-minidump-upload ${PN}-sym-upload"

FILES_${PN}-minidump-upload = "${bindir}/minidump_upload"
FILES_${PN}-sym-upload = "${bindir}/sym_upload"


SYSROOT_PREPROCESS_FUNCS += "breakpad_populate_sysroot"
breakpad_populate_sysroot() {
        sysroot_stage_dir ${D}/usr/include ${SYSROOT_DESTDIR}/usr/include
        sysroot_stage_dir ${D}/usr/lib ${SYSROOT_DESTDIR}/usr/lib
        sysroot_stage_dir ${D}/usr/lib ${SYSROOT_DESTDIR}/usr/lib
}

