TARGET = Blinky
SPINAL_SRC_DIR = src/main/scala/mylib/

NEXTPNR_DEVICE = hx8k
PACKAGE        = ct256
ICETIME_DEVICE = hx8k

PROG_BIN       = iceprog

PINS_FILE = ./src/Ice40/pins.pcf

YOSYS_LOG  = synth.log
YOSYS_ARGS = -v3 -l $(YOSYS_LOG)

SPINAL_SRCS = $(wildcard $(SPINAL_SRC_DIR)*.scala)
VERILOG_SRCS = $(TARGET).v

BIN_FILE  = $(TARGET).bin
ASC_FILE  = $(TARGET).asc
JSON_FILE = $(TARGET).json

all:	$(BIN_FILE)

$(BIN_FILE):	$(ASC_FILE)
	icepack	$< $@

$(ASC_FILE):	$(JSON_FILE) $(PINS_FILE)
	nextpnr-ice40 --$(NEXTPNR_DEVICE) --package $(PACKAGE) --json $(JSON_FILE) --pcf $(PINS_FILE) --asc $(ASC_FILE)

$(JSON_FILE):	$(VERILOG_SRCS)
	yosys $(YOSYS_ARGS) -p "synth_ice40 -json $(JSON_FILE)" $(VERILOG_SRCS)

$(VERILOG_SRCS):	$(SPINAL_SRCS)
	sbt "runMain mylib.$(TARGET)Verilog"
	
prog:	$(BIN_FILE)
	$(PROG_BIN) $<
	
sim:	$(VERILOG_SRCS)
	sbt "runMain mylib.$(TARGET)Sim"
	
simwave:	$(VERILOG_SRCS)
	sbt "runMain mylib.$(TARGET)SimWave"
	gtkwave simWorkspace/$(TARGET)/test.vcd signals.gtkw
	
timings:$(ASC_FILE)
	icetime -tmd $(ICETIME_DEVICE) $<

clean:
	sbt clean
	rm -f $(BIN_FILE) $(ASC_FILE) $(JSON_FILE) $(YOSYS_LOG) $(VERILOG_SRCS)
	rm -rf simWorkspace
	rm -rf tmp

.PHONY:	all clean prog timings


